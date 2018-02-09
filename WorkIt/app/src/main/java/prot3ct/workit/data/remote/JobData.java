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
    public Observable<Boolean> createTask(String title, String startDate, String endDate,
                                          String description, String country, String city, String address,
                                          String reward, String minimalRating, String minimalJobsCompleted) {
        Map<String, String> jobDetails = new HashMap<>();
        jobDetails.put("title", title);
        jobDetails.put("startDate", startDate);
        jobDetails.put("endDate", endDate);
        jobDetails.put("description", description);
        jobDetails.put("country", country);
        jobDetails.put("city", city);
        jobDetails.put("address", address);
        jobDetails.put("reward", reward);
        jobDetails.put("minRaiting", minimalRating);
        jobDetails.put("minTasksCompleted", minimalJobsCompleted);
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
}
