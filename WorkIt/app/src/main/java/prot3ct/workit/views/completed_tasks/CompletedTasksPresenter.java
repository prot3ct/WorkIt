package prot3ct.workit.views.completed_tasks;

import android.content.Context;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.JobData;
import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.models.Task;
import prot3ct.workit.views.completed_tasks.base.CompletedTasksContract;

public class CompletedTasksPresenter implements CompletedTasksContract.Presenter {
    private CompletedTasksContract.View view;
    private JobData jobData;
    private UserData userData;

    public CompletedTasksPresenter(CompletedTasksContract.View view, Context context) {
        this.view = view;
        this.jobData = new JobData(context);
        this.userData = new UserData(context);
    }

    @Override
    public void getMyTasks() {
        jobData.getMyCompletedTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<List<Task>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        view.showDialogForLoading();
                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        view.setupTasksAdapter(tasks);
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

    @Override
    public void createRating(int receiverUserId, int taskId, int receiverUserRoleId, String value) {
        userData.createRaiting(receiverUserId, taskId, receiverUserRoleId, value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                view.showDialogForLoading();
                            }

                            @Override
                            public void onNext(Boolean result) {
                                view.dismissDialog();
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.dismissDialog();
                                view.notifyError("Error ocurred when retrieving data. Please try again.");
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
    }
}
