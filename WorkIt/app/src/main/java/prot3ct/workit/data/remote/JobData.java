package prot3ct.workit.data.remote;

import android.content.Context;

import java.lang.reflect.Type;
import java.util.HashMap;
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
    private final Type jobModelType;

    public JobData(Context context) {
        this.jsonParser = new GsonParser();
        this.httpRequester = new OkHttpRequester();
        this.apiConstants = new ApiConstants();
        this.userSession = new UserSession(context);
        this.jobModelType = Task.class;
    }

    @Override
    public Observable<Boolean> createTask(String title, String startDate, String endDate,
                                          String description, String country, String city, String address,
                                          String reward, String minimalRating, String minimalJobsCompleted) {
        Map<String, String> jobDetails = new HashMap<>();
        jobDetails.put("title", title);
        jobDetails.put("startDate", startDate);
        jobDetails.put("startDate", endDate);
        jobDetails.put("startDate", description);
        jobDetails.put("startDate", country);
        jobDetails.put("startDate", city);
        jobDetails.put("startDate", address);
        jobDetails.put("startDate", reward);
        jobDetails.put("startDate", minimalRating);
        jobDetails.put("startDate", minimalJobsCompleted);

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
}
