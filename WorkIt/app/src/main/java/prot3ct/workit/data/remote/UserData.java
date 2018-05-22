package prot3ct.workit.data.remote;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import prot3ct.workit.config.ApiConstants;
import prot3ct.workit.data.local.UserSession;
import prot3ct.workit.data.remote.base.UserDataContract;
import prot3ct.workit.models.base.HttpResponseContract;
import prot3ct.workit.utils.GsonParser;
import prot3ct.workit.utils.HashProvider;
import prot3ct.workit.utils.OkHttpRequester;
import prot3ct.workit.view_models.ProfileDetailsViewModel;

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
    public Observable<ProfileDetailsViewModel> getProfileDetails(int userId) {
        return httpRequester
            .get(apiConstants.getProfileDetailsUrl(userId))
            .map(new Function<HttpResponseContract, ProfileDetailsViewModel>() {
                @Override
                public ProfileDetailsViewModel apply(HttpResponseContract iHttpResponse) throws Exception {
                    if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                        throw new Error(iHttpResponse.getMessage());
                    }

                    String responseBody = iHttpResponse.getBody();
                    return jsonParser.fromJson(responseBody, ProfileDetailsViewModel.class);
                }
            });
    }

    @Override
    public Observable<Boolean> updateProfile(String fullName, String phone, String profilePictureAsString) {
        Map<String, String> profileDetails = new HashMap<>();
        profileDetails.put("userId", userSession.getId()+"");
        profileDetails.put("fullName", fullName);
        profileDetails.put("phone", phone);
        profileDetails.put("profilePictureAsString", profilePictureAsString);
        Log.d("CEKOO", userSession.getId()+"");
        Log.d("CEKOO", fullName);
        Log.d("CEKOO", phone);
        Log.d("CEKOO", profilePictureAsString+"123");

        return httpRequester
                .put(apiConstants.updateProfile(userSession.getId()), profileDetails)
                .map(new Function<HttpResponseContract, Boolean>() {
                    @Override
                    public Boolean apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                            Log.d("CEKOO1", iHttpResponse.getBody());
                            Log.d("CEKOO1", iHttpResponse.getMessage());
                            throw new Error(iHttpResponse.getMessage());
                        }

                        return true;
                    }
                });
    }
}
