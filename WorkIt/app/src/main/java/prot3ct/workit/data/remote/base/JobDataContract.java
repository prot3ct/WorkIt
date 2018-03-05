package prot3ct.workit.data.remote.base;

import java.util.List;

import io.reactivex.Observable;
import prot3ct.workit.models.Task;

public interface JobDataContract {
    Observable<Boolean> createTask(String title, String startDate, String endDate,
                                   String description, String country, String city, String address,
                                   String reward, String minimalRating, String minimalJobsCompleted);

    Observable<List<Task>> getAllTasks();

    Observable<List<Task>> getMyTasks();
}
