package prot3ct.workit.views.list_pending_requests;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.TaskRequestData;
import prot3ct.workit.data.remote.result_models.TaskRequestListViewModel;
import prot3ct.workit.views.list_pending_requests.base.ListPendingRequestsContract;

public class ListPendingRequestsPresenter implements ListPendingRequestsContract.Presenter {
    private ListPendingRequestsContract.View view;
    private TaskRequestData taskRequestData;

    public ListPendingRequestsPresenter(ListPendingRequestsContract.View view, Context context) {
        this.view = view;
        this.taskRequestData = new TaskRequestData(context);
    }

    @Override
    public void getTaskRequestsForCurrentUser() {
        taskRequestData.getAllTaskRequestsForUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<List<TaskRequestListViewModel>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
//                        view.showDialogForLoading();
                            }

                            @Override
                            public void onNext(List<TaskRequestListViewModel> taskRequests) {
                                view.setupTaskRequestsAdapter(taskRequests);
//                                view.notifySuccessful();
//                                view.showListJobsActivity();
                            }

                            @Override
                            public void onError(Throwable e) {
//                                view.notifyError("Error ocurred when logining in. Please try again.");
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
    }
}
