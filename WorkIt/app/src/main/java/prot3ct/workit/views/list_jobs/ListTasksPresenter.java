package prot3ct.workit.views.list_jobs;

import android.content.Context;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.TaskData;
import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.view_models.AvailableTasksListViewModel;
import prot3ct.workit.views.list_jobs.base.ListTasksContract;

public class ListTasksPresenter implements ListTasksContract.Presenter {
    private ListTasksContract.View view;
    private UserData userData;
    private TaskData taskData;

    public ListTasksPresenter(ListTasksContract.View view, Context context) {
        this.view = view;
        this.userData = new UserData(context);
        this.taskData = new TaskData(context);
    }

    @Override
    public void getAllTasks() {
        taskData.getAvailableTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<List<AvailableTasksListViewModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        view.showDialogForLoading();
                    }

                    @Override
                    public void onNext(List<AvailableTasksListViewModel> tasks) {
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
