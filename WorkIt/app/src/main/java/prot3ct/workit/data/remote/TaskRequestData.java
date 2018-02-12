package prot3ct.workit.data.remote;


import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import prot3ct.workit.config.ApiConstants;
import prot3ct.workit.data.local.UserSession;
import prot3ct.workit.data.remote.base.TaskRequestDataContract;
import prot3ct.workit.data.remote.result_models.TaskRequestDetailsViewModel;
import prot3ct.workit.data.remote.result_models.TaskRequestListViewModel;
import prot3ct.workit.models.base.HttpResponseContract;
import prot3ct.workit.utils.GsonParser;
import prot3ct.workit.utils.OkHttpRequester;

public class TaskRequestData implements TaskRequestDataContract {
    private final OkHttpRequester httpRequester;
    private final ApiConstants apiConstants;
    private final GsonParser jsonParser;
    private final UserSession userSession;

    public TaskRequestData(Context context) {
        this.jsonParser = new GsonParser();
        this.httpRequester = new OkHttpRequester();
        this.apiConstants = new ApiConstants();
        this.userSession = new UserSession(context);
    }

    @Override
    public Observable<TaskRequestDetailsViewModel> getTaskRequestBtId(int taskRequestId) {
        return httpRequester
                .get(apiConstants.getTaskRequestByIdUrl(taskRequestId))
                .map(new Function<HttpResponseContract, TaskRequestDetailsViewModel>() {
                    @Override
                    public TaskRequestDetailsViewModel apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                            throw new Error(iHttpResponse.getMessage());
                        }

                        String responseBody = iHttpResponse.getBody();
                        TaskRequestDetailsViewModel taskRequestDetails = jsonParser.fromJson(responseBody, TaskRequestDetailsViewModel.class);
                        return taskRequestDetails;
                    }
                });
    }

    @Override
    public Observable<Boolean> createTaskRequest(String descritpion, int taskId) {
        Map<String, String> taskRequest = new HashMap<>();
        taskRequest.put("description", descritpion);
        taskRequest.put("taskId", Integer.toString(taskId));
        taskRequest.put("userId", Integer.toString(userSession.getId()));

        return httpRequester
            .post(apiConstants.createTaskRequestUrl(), taskRequest)
            .map(new Function<HttpResponseContract, Boolean>() {
                @Override
                public Boolean apply(HttpResponseContract iHttpResponse) throws Exception {
                    if (iHttpResponse.getCode() == apiConstants.responseErrorCode() || iHttpResponse.getCode() == apiConstants.reponseServerErrorCode()) {
                        throw new Error(iHttpResponse.getMessage());
                    }

                    return true;
                }
            });
    }

    @Override
    public Observable<List<TaskRequestListViewModel>> getAllTaskRequestsForUser() {
        return httpRequester
            .get(apiConstants.getTaskRequestsForCurrentUserUrl(userSession.getId()))
            .map(new Function<HttpResponseContract, List<TaskRequestListViewModel>>() {
                @Override
                public List<TaskRequestListViewModel> apply(HttpResponseContract iHttpResponse) throws Exception {
                    if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                        throw new Error(iHttpResponse.getMessage());
                    }

                    String responseBody = iHttpResponse.getBody();
                    List<TaskRequestListViewModel> taskRequests = jsonParser.fromJson(responseBody, new TypeToken<List<TaskRequestListViewModel>>(){}.getType());
                    return taskRequests;
                }
            });
    }
}
