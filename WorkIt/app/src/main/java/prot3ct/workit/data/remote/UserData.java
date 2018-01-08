package prot3ct.workit.data.remote;

import android.content.Context;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import prot3ct.workit.config.ApiConstants;
import prot3ct.workit.data.local.UserSession;
import prot3ct.workit.data.remote.base.UserDataContract;
import prot3ct.workit.models.User;
import prot3ct.workit.models.base.HttpResponseContract;
import prot3ct.workit.models.base.UserContract;
import prot3ct.workit.utils.GsonParser;
import prot3ct.workit.utils.HashProvider;
import prot3ct.workit.utils.OkHttpRequester;

public class UserData implements UserDataContract {
    private final OkHttpRequester httpRequester;
    private final HashProvider hashProvider;
    private final ApiConstants apiConstants;
    private final GsonParser jsonParser;
    private final UserSession userSession;
    private final Type userModelType;

    public UserData(Context context) {
        this.jsonParser = new GsonParser();
        this.hashProvider = new HashProvider();
        this.httpRequester = new OkHttpRequester();
        this.apiConstants = new ApiConstants();
        this.userSession = new UserSession(context);
        this.userModelType = User.class;
    }

    @Override
    public Observable<UserContract> login(String username, String password) {
        Map<String, String> userCredentials = new HashMap<>();
        String passHash = hashProvider.hashPassword(password);
        userCredentials.put("username", username.toLowerCase());
        userCredentials.put("passHash", passHash);

        return httpRequester
            .post(apiConstants.loginUrl(), userCredentials)
            .map(new Function<HttpResponseContract, UserContract>() {
                @Override
                public UserContract apply(HttpResponseContract iHttpResponse) throws Exception {
                    if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                        throw new Error(iHttpResponse.getMessage());
                    }
                    String responseBody = iHttpResponse.getBody();
                    String userJson = jsonParser.getDirectMember(responseBody, "result");
                    UserContract resultUser = jsonParser.fromJson(userJson, userModelType);

                    userSession.setEmail(resultUser.getEmail());
                    return resultUser;
                }
            });
    }

    @Override
    public Observable<UserContract> register(String email, String firstname, String lastname, String password) {
        Map<String, String> userCredentials = new HashMap<>();
        String passHash = hashProvider.hashPassword(password);
        userCredentials.put("email", email);
        userCredentials.put("firstname", firstname);
        userCredentials.put("lastname", lastname);
        userCredentials.put("passHash", passHash);

        return httpRequester
                .post(apiConstants.registerUrl(), userCredentials)
                .map(new Function<HttpResponseContract, UserContract>() {
                    @Override
                    public UserContract apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                            throw new Error(iHttpResponse.getMessage());
                        }

                        String responseBody = iHttpResponse.getBody();

                        String userJson = jsonParser.getDirectMember(responseBody, "result");
                        return jsonParser.fromJson(userJson, userModelType);
                    }
                });
    }
}
