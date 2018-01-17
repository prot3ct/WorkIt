package prot3ct.workit.views.list_jobs;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.JobData;
import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.models.Task;
import prot3ct.workit.views.list_jobs.base.ListJobsContract;

public class ListJobsPresenter implements ListJobsContract.Presenter {
    private ListJobsContract.View view;
    private UserData userData;
    private JobData jobData;

    public ListJobsPresenter(ListJobsContract.View view, Context context) {
        this.view = view;
        this.userData = new UserData(context);
        this.jobData = new JobData(context);
    }

    @Override
    public void logout() {
        this.userData.logoutUser();
    }

    @Override
    public void getAllTasks() {
        jobData.getAllTasks()
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
                                Log.d("CEKO", e.getMessage());
//                                view.notifyError("Error ocurred when logining in. Please try again.");
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
    }
}
