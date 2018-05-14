package prot3ct.workit.views.list_task_requests;

import android.content.Context;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.TaskRequestData;
import prot3ct.workit.data.remote.result_models.TaskRequestListViewModel;
import prot3ct.workit.views.list_task_requests.base.ListTaskRequestContract;

public class ListTaskRequestsPresenter implements  ListTaskRequestContract.Presenter {
    private ListTaskRequestContract.View view;
    private TaskRequestData taskRequestData;

    public ListTaskRequestsPresenter(ListTaskRequestContract.View view, Context context) {
        this.view = view;
        this.taskRequestData = new TaskRequestData(context);
    }

    @Override
    public void getTaskRequests(int taskId) {
        taskRequestData.getAllTaskRequestsForTask(taskId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<List<TaskRequestListViewModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<TaskRequestListViewModel> taskRequests) {
                        view.setupTaskRequestsAdapter(taskRequests);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.notifyError("Error loading task requests.");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
