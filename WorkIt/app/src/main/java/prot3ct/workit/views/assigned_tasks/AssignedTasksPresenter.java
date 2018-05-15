package prot3ct.workit.views.assigned_tasks;

import android.content.Context;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.TaskData;
import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.view_models.AssignedTasksListViewModel;
import prot3ct.workit.views.assigned_tasks.base.AssignedTasksContract;

public class AssignedTasksPresenter implements AssignedTasksContract.Presenter {
    private AssignedTasksContract.View view;
    private UserData userData;
    private TaskData taskData;

    public AssignedTasksPresenter(AssignedTasksContract.View view, Context context) {
        this.view = view;
        this.userData = new UserData(context);
        this.taskData = new TaskData(context);
    }

    @Override
    public void getAssignedTasks() {
        taskData.getAssignedTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<List<AssignedTasksListViewModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        view.showDialogForLoading();
                    }

                    @Override
                    public void onNext(List<AssignedTasksListViewModel> tasks) {
                        view.setupTasksAdapter(tasks);
//                                view.notifySuccessful();
//                                view.showListJobsActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.notifyError("Error ocurred when loading tasks.");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}
