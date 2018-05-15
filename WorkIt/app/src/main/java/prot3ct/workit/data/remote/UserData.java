package prot3ct.workit.data.remote;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import prot3ct.workit.config.ApiConstants;
import prot3ct.workit.data.local.UserSession;
import prot3ct.workit.data.remote.base.UserDataContract;
import prot3ct.workit.view_models.LoginViewModel;
import prot3ct.workit.models.base.HttpResponseContract;
import prot3ct.workit.utils.GsonParser;
import prot3ct.workit.utils.HashProvider;
import prot3ct.workit.utils.OkHttpRequester;

public class UserData implements UserDataContract {
    private final OkHttpRequester httpRequester;
    private final HashProvider hashProvider;
    private final ApiConstants apiConstants;
    private final GsonParser jsonParser;
    private final UserSession userSession;

    public UserData(Context context) {
        this.jsonParser = new GsonParser();
        this.hashProvider = new HashProvider();
        this.httpRequester = new OkHttpRequester();
        this.apiConstants = new ApiConstants();
        this.userSession = new UserSession(context);
    }

    @Override
    public Observable<Boolean> login(String email, String password) {
        final Map<String, String> userCredentials = new HashMap<>();
        String passHash = hashProvider.hashPassword(password);
        userCredentials.put("email", email);
        userCredentials.put("passHash", passHash);

        return httpRequester
            .post(apiConstants.loginUrl(), userCredentials)
            .map(new Function<HttpResponseContract, Boolean>() {
                @Override
                public Boolean apply(HttpResponseContract iHttpResponse) throws Exception {
                    if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                        throw new Error(iHttpResponse.getMessage());
                    }
                    String responseBody = iHttpResponse.getBody();
                    LoginViewModel result = jsonParser.fromJson(responseBody, LoginViewModel.class);
                    userSession.setId(result.getUserId());
                    userSession.setEmail(result.getEmail());
                    userSession.setFullName(result.getFullName());
                    return true;
                }
            });
    }

    @Override
    public Observable<Boolean> register(String email, String fullName, String password) {
        Map<String, String> userCredentials = new HashMap<>();
        String passHash = hashProvider.hashPassword(password);
        userCredentials.put("email", email);
        userCredentials.put("fullName", fullName);
        userCredentials.put("passHash", passHash);

        return httpRequester
                .post(apiConstants.registerUrl(), userCredentials)
                .map(new Function<HttpResponseContract, Boolean>() {
                    @Override
                    public Boolean apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                            throw new Error(iHttpResponse.getMessage());
                        }

                        return true;
                    }
                });
    }

    @Override
    public int getLoggedInUserId() {
        return this.userSession.getId();
    }

    @Override
    public void logoutUser() {
        this.userSession.clearSession();
    }

    @Override
    public boolean isLoggedIn() {
        return this.userSession.isUserLoggedIn();
    }
}
