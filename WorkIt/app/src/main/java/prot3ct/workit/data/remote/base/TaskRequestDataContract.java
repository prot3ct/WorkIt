package prot3ct.workit.data.remote.base;

import io.reactivex.Observable;

public interface TaskRequestDataContract {
    Observable<Boolean> createTask(String descritpion, int taskId);
}
