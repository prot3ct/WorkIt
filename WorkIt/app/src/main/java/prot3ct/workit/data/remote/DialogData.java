package prot3ct.workit.data.remote;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import prot3ct.workit.config.ApiConstants;
import prot3ct.workit.data.local.UserSession;
import prot3ct.workit.models.base.HttpResponseContract;
import prot3ct.workit.utils.GsonParser;
import prot3ct.workit.utils.OkHttpRequester;
import prot3ct.workit.view_models.DialogsListViewModel;

public class DialogData {
    private final OkHttpRequester httpRequester;
    private final ApiConstants apiConstants;
    private final GsonParser jsonParser;
    private final UserSession userSession;

    public DialogData(Context context) {
        this.jsonParser = new GsonParser();
        this.httpRequester = new OkHttpRequester();
        this.apiConstants = new ApiConstants();
        this.userSession = new UserSession(context);
    }

    public Observable<String> createDialog(int userId) {
        Map<String, String> dialogDetails = new HashMap<>();
        dialogDetails.put("user1Id", userSession.getId()+"");
        dialogDetails.put("user2Id", userId+"");

        return httpRequester
                .post(apiConstants.createDialog(), dialogDetails)
                .map(new Function<HttpResponseContract, String>() {
                    @Override
                    public String apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode() || iHttpResponse.getCode() == apiConstants.reponseServerErrorCode()) {
                            throw new Error(iHttpResponse.getMessage());
                        }
                        Log.d("CEKO123", iHttpResponse.getBody());

                        return iHttpResponse.getBody();
                    }
                });
    }

    public Observable<List<DialogsListViewModel>> getDialogs() {
        return httpRequester
                .get(apiConstants.getDialogs(userSession.getId()))
                .map(new Function<HttpResponseContract, List<DialogsListViewModel>>() {
                    @Override
                    public List<DialogsListViewModel> apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                            throw new Error(iHttpResponse.getMessage());
                        }

                        String responseBody = iHttpResponse.getBody();
                        return jsonParser.fromJson(responseBody, new TypeToken<List<DialogsListViewModel>>(){}.getType());
                    }
                });
    }
}
