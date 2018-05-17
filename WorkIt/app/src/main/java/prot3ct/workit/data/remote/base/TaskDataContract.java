package prot3ct.workit.data.remote.base;

import java.util.List;

import io.reactivex.Observable;
import prot3ct.workit.view_models.AssignedTasksListViewModel;
import prot3ct.workit.view_models.AvailableTasksListViewModel;
import prot3ct.workit.view_models.CompletedTasksListViewModel;
import prot3ct.workit.view_models.MyTasksListViewModel;
import prot3ct.workit.view_models.TaskDetailViewModel;

public interface TaskDataContract {
    Observable<Boolean> createTask(String title, String startDate, String length,
                                   String description, String city, String address, String reward);

    Observable<Boolean> updateTask(int taskId, String title, String startDate, String length,
                                   String description, String city, String address, String reward);

    Observable<TaskDetailViewModel> getTaskDetails(int taskId);

    Observable<Boolean> deleteTask(int taskId);

    Observable<List<AssignedTasksListViewModel>> getAssignedTasks();

    Observable<List<AvailableTasksListViewModel>> getAvailableTasks();

    Observable<List<MyTasksListViewModel>> getMyTasks();

    Observable<List<CompletedTasksListViewModel>> getCompletedTasks();
}
