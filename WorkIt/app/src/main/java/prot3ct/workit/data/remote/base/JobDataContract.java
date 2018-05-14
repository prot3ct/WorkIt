package prot3ct.workit.data.remote.base;

import java.util.List;

import io.reactivex.Observable;
import prot3ct.workit.data.remote.result_models.EditTaskViewModel;
import prot3ct.workit.models.Task;

public interface JobDataContract {
    Observable<Boolean> createTask(String title, String startDate, String length,
                                   String description, String city, String address,
                                   String reward, String minimalRating);

    Observable<Boolean> updateTask(int taskId, String title, String startDate, String length,
                                   String description, String city, String address,
                                   String reward, String minimalRating);

    Observable<EditTaskViewModel> getTaskById(int taskId);

    Observable<List<Task>> getAllTasks();

    Observable<List<Task>> getMyTasks();

    Observable<List<Task>> getMyCompletedTasks();
}
