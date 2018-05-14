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
import prot3ct.workit.data.remote.base.JobDataContract;
import prot3ct.workit.data.remote.result_models.EditTaskViewModel;
import prot3ct.workit.models.Task;
import prot3ct.workit.models.base.HttpResponseContract;
import prot3ct.workit.utils.GsonParser;
import prot3ct.workit.utils.OkHttpRequester;

public class JobData implements JobDataContract{
    private final OkHttpRequester httpRequester;
    private final ApiConstants apiConstants;
    private final GsonParser jsonParser;
    private final UserSession userSession;

    public JobData(Context context) {
        this.jsonParser = new GsonParser();
        this.httpRequester = new OkHttpRequester();
        this.apiConstants = new ApiConstants();
        this.userSession = new UserSession(context);
    }

    @Override
    public Observable<Boolean> createTask(String title, String startDate, String length,
                                          String description, String city, String address,
                                          String reward, String minimalRating) {
        Map<String, String> jobDetails = new HashMap<>();
        jobDetails.put("title", title);
        jobDetails.put("startDate", startDate);
        jobDetails.put("length", length);
        jobDetails.put("description", description);
        jobDetails.put("city", city);
        jobDetails.put("address", address);
        jobDetails.put("reward", reward);
        jobDetails.put("minRaiting", minimalRating);
        jobDetails.put("creatorEmail", this.userSession.getEmail().replaceAll("\"", ""));

        return httpRequester
                .post(apiConstants.createTaskUrl(), jobDetails)
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
    public Observable<Boolean> updateTask(int taskId, String title, String startDate, String length,
                                          String description, String city, String address,
                                          String reward, String minimalRating) {
        Map<String, String> jobDetails = new HashMap<>();
        jobDetails.put("id", taskId + "");
        jobDetails.put("title", title);
        jobDetails.put("startDate", startDate);
        jobDetails.put("length", length);
        jobDetails.put("description", description);
        jobDetails.put("city", city);
        jobDetails.put("address", address);
        jobDetails.put("reward", reward);
        jobDetails.put("minRaiting", minimalRating);

        return httpRequester
                .post(apiConstants.updateTaskUrl(taskId), jobDetails)
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
    public Observable<List<Task>> getAllTasks() {
        return httpRequester
            .get(apiConstants.getAllJobsUrl())
            .map(new Function<HttpResponseContract, List<Task>>() {
                @Override
                public List<Task> apply(HttpResponseContract iHttpResponse) throws Exception {
                    if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                        throw new Error(iHttpResponse.getMessage());
                    }

                    String responseBody = iHttpResponse.getBody();
                    List<Task> tasks = jsonParser.fromJson(responseBody, new TypeToken<List<Task>>(){}.getType());
                    return tasks;
                }
            });
    }

    @Override
    public Observable<EditTaskViewModel> getTaskById(int taskId) {
        return httpRequester
                .get(apiConstants.getTaskByIdUrl(taskId))
                .map(new Function<HttpResponseContract, EditTaskViewModel>() {
                    @Override
                    public EditTaskViewModel apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                            throw new Error(iHttpResponse.getMessage());
                        }

                        String responseBody = iHttpResponse.getBody();
                        return jsonParser.fromJson(responseBody, EditTaskViewModel.class);
                    }
                });
    }

    @Override
    public Observable<List<Task>> getMyTasks() {
        return httpRequester
            .get(apiConstants.getAllTasksUrl(this.userSession.getId()))
            .map(new Function<HttpResponseContract, List<Task>>() {
                @Override
                public List<Task> apply(HttpResponseContract iHttpResponse) throws Exception {
                if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                    throw new Error(iHttpResponse.getMessage());
                }

                String responseBody = iHttpResponse.getBody();
                List<Task> tasks = jsonParser.fromJson(responseBody, new TypeToken<List<Task>>(){}.getType());
                return tasks;
                }
            });
    }

    @Override
    public Observable<List<Task>> getMyCompletedTasks() {
        return httpRequester
                .get(apiConstants.getMyCompletedTasksUrl(this.userSession.getId()))
                .map(new Function<HttpResponseContract, List<Task>>() {
                    @Override
                    public List<Task> apply(HttpResponseContract iHttpResponse) throws Exception {
                        if (iHttpResponse.getCode() == apiConstants.responseErrorCode()) {
                            throw new Error(iHttpResponse.getMessage());
                        }

                        String responseBody = iHttpResponse.getBody();
                        List<Task> tasks = jsonParser.fromJson(responseBody, new TypeToken<List<Task>>(){}.getType());
                        return tasks;
                    }
                });
    }
}
