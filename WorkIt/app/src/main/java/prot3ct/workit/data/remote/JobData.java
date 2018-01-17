package prot3ct.workit.data.remote;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import prot3ct.workit.config.ApiConstants;
import prot3ct.workit.data.local.UserSession;
import prot3ct.workit.data.remote.base.JobDataContract;
import prot3ct.workit.models.JobDTO;
import prot3ct.workit.models.Task;
import prot3ct.workit.models.base.HttpResponseContract;
import prot3ct.workit.utils.GsonParser;
import prot3ct.workit.utils.OkHttpRequester;

public class JobData implements JobDataContract{
    private final OkHttpRequester httpRequester;
    private final ApiConstants apiConstants;
    private final GsonParser jsonParser;
    private final UserSession userSession;
    private final Type taskModelType;

    public JobData(Context context) {
        this.jsonParser = new GsonParser();
        this.httpRequester = new OkHttpRequester();
        this.apiConstants = new ApiConstants();
        this.userSession = new UserSession(context);
        this.taskModelType = Task.class;
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
        jobDetails.put("minimalRating", minimalRating);
        jobDetails.put("minimalJobsCompleted", minimalJobsCompleted);

        return httpRequester
                .post(apiConstants.createTaskUrl(), jobDetails)
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
                        Log.d("CEKO2", responseBody);
                        List<JobDTO> tasks = jsonParser.fromJson(responseBody, JobDTO[].class);
                        for (JobDTO t: tasks) {
                            Log.d("CEKO2", t.getTitle());
                        }
                        return new Stack<Task>();
                    }
                });
    }
}
