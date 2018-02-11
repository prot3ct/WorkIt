package prot3ct.workit.data.remote.base;

import java.util.List;

import io.reactivex.Observable;
import prot3ct.workit.data.remote.result_models.TaskRequestResult;

public interface TaskRequestDataContract {
    Observable<Boolean> createTaskRequest(String descritpion, int taskId);

    Observable<List<TaskRequestResult>> getAllTaskRequestsForUser();
}
