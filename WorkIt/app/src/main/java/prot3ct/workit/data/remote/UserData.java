package prot3ct.workit.data.remote;

import android.content.Context;
import android.provider.ContactsContract;

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
    public Observable<ProfileDetailsViewModel> getProfileDetails() {
        return httpRequester
            .get(apiConstants.getProfileDetailsUrl(userSession.getId()))
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
}
