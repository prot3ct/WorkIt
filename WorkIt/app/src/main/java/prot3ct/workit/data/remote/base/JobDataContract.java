package prot3ct.workit.data.remote.base;

import io.reactivex.Observable;

public interface JobDataContract {
    Observable<Boolean> createTask(String title, String startDate, String endDate,
                                   String description, String country, String city, String address,
                                   String reward, String minimalRating, String minimalJobsCompleted);
}
