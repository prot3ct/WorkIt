package prot3ct.workit.views.task_request_details;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.TaskRequestData;
import prot3ct.workit.data.remote.result_models.TaskRequestDetailsViewModel;
import prot3ct.workit.data.remote.result_models.TaskRequestListCommentsViewModel;
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
        taskRequestData.getTaskRequestById(taskRequestId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<TaskRequestDetailsViewModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showDialogforLoading();
                    }

                    @Override
                    public void onNext(TaskRequestDetailsViewModel taskRequest) {
                        view.updateView(taskRequest.getTaskTitle(), taskRequest.getDescription());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void createTaskRequestComment(int taskRequestId, String body) {
        taskRequestData.createTaskRequestComment(taskRequestId, body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showDialogforLoading();
                    }

                    @Override
                    public void onNext(Boolean taskRequest) {
                        view.notifySuccessful("Comment has been created successfully");
                        view.setupComments();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.notifyError("Error ocurred when creating comment. Please try again.");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getTaskRequestComments(int taskRequestId) {
        taskRequestData.getTaskRequestComments(taskRequestId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<List<TaskRequestListCommentsViewModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        view.showDialogforLoading();
                    }

                    @Override
                    public void onNext(List<TaskRequestListCommentsViewModel> taskRequestComments) {
                        view.setupTaskRequestsAdapter(taskRequestComments);
                        view.dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.dismissDialog();
                        view.notifyError("Error ocurred when getting comments. Please try again.");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
