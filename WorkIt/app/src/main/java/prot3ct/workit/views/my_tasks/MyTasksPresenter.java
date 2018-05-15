package prot3ct.workit.views.my_tasks;

import android.content.Context;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.TaskData;
import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.view_models.MyTasksListViewModel;
import prot3ct.workit.views.my_tasks.base.MyTasksContract;

public class MyTasksPresenter implements MyTasksContract.Presenter {
    private MyTasksContract.View view;
    private UserData userData;
    private TaskData taskData;

    public MyTasksPresenter(MyTasksContract.View view, Context context) {
        this.view = view;
        this.userData = new UserData(context);
        this.taskData = new TaskData(context);
    }

    @Override
    public void getMyTasks() {
        taskData.getMyTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<List<MyTasksListViewModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<MyTasksListViewModel> tasks) {
                        view.setupTasksAdapter(tasks);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.notifyError("Error ocurred loading tasks.");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
