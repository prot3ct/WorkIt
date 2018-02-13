package prot3ct.workit.data.remote.base;

import java.util.List;

import io.reactivex.Observable;
import prot3ct.workit.data.remote.result_models.TaskRequestDetailsViewModel;
import prot3ct.workit.data.remote.result_models.TaskRequestListCommentsViewModel;
import prot3ct.workit.data.remote.result_models.TaskRequestListViewModel;

public interface TaskRequestDataContract {
    Observable<Boolean> createTaskRequest(String descritpion, int taskId);

    Observable<List<TaskRequestListViewModel>> getAllTaskRequestsForUser();

    Observable<TaskRequestDetailsViewModel> getTaskRequestById(int id);

    Observable<Boolean> createTaskRequestComment(int taskRequestId, String body);

    Observable<List<TaskRequestListCommentsViewModel>> getTaskRequestComments(int taskRequestId);
}
