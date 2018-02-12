package prot3ct.workit.views.task_request_details;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.TaskRequestData;
import prot3ct.workit.data.remote.result_models.TaskRequestDetailsViewModel;
import prot3ct.workit.views.task_request_details.base.TaskRequestDetailsContract;

public class TaskRequestDetailsPresenter implements  TaskRequestDetailsContract.Presenter {
    private TaskRequestDetailsContract.View view;
    private TaskRequestData taskRequestData;

    public TaskRequestDetailsPresenter(TaskRequestDetailsContract.View view, Context context) {
        this.view = view;
        this.taskRequestData = new TaskRequestData(context);
    }

    @Override
    public void getTaskRequestById(int taskRequestId) {
        taskRequestData.getTaskRequestBtId(taskRequestId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<TaskRequestDetailsViewModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //view.showDialogforLoading();
                    }

                    @Override
                    public void onNext(TaskRequestDetailsViewModel taskRequest) {
                        view.updateView(taskRequest.getTaskTitle(), taskRequest.getDescription());
                        //view.dismissDialog();
//                                redirect to requests view
                    }

                    @Override
                    public void onError(Throwable e) {
                        //view.notifyError("Error ocurred when sending request. Please try again.");
//                        view.dismissDialog();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
