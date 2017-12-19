package prot3ct.workit.data.remote;

import android.content.Context;

import java.lang.reflect.Type;

import prot3ct.workit.config.ApiConstants;
import prot3ct.workit.data.local.UserSession;
import prot3ct.workit.data.remote.base.UserDataContract;
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
        this.userModelType = null;//User.class;
    }
}
