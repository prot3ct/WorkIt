package prot3ct.workit.views.my_tasks;

import android.content.Context;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.JobData;
import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.models.Task;
import prot3ct.workit.views.my_tasks.base.MyTasksContract;

public class MyTasksPresenter implements MyTasksContract.Presenter {
    private MyTasksContract.View view;
    private UserData userData;
    private JobData jobData;

    public MyTasksPresenter(MyTasksContract.View view, Context context) {
        this.view = view;
        this.userData = new UserData(context);
        this.jobData = new JobData(context);
    }

    @Override
    public void getMyTasks() {
        jobData.getMyTasks()
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
}
