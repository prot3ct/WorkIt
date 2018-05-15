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
import prot3ct.workit.data.remote.base.TaskRequestDataContract;
import prot3ct.workit.view_models.TaskRequestListViewModel;
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
    public Observable<Boolean> createTaskRequest(int taskId) {
        Map<String, String> taskRequest = new HashMap<>();
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
    public Observable<Boolean> updateTaskRequest(int taskRequestId, int status) {
        Map<String, String> body = new HashMap<>();
        body.put("taskRequestId", Integer.toString(taskRequestId));
        body.put("requestStatusId", Integer.toString(status));

        return httpRequester
                .post(apiConstants.updateTaskRequestUrl(taskRequestId), body)
                .map(new Function<HttpResponseContract, Boolean>() {
                    @Override
                    public Boolean apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                            throw new Error(iHttpResponse.getBody());
                        }

                        return true;
                    }
                });
    }

    @Override
    public Observable<List<TaskRequestListViewModel>> getAllTaskRequestsForTask(int taskId) {
        return httpRequester
                .get(apiConstants.getRequestsForTaskUrl(taskId))
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
