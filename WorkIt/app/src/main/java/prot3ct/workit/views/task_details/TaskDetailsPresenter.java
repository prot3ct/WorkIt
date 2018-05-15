package prot3ct.workit.views.task_details;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.TaskData;
import prot3ct.workit.data.remote.LocationData;
import prot3ct.workit.data.remote.TaskRequestData;
import prot3ct.workit.models.base.LocationContract;
import prot3ct.workit.view_models.TaskDetailViewModel;
import prot3ct.workit.views.task_details.base.TaskDetailsContract;

public class TaskDetailsPresenter implements TaskDetailsContract.Presenter {
    private TaskDetailsContract.View view;
    private TaskRequestData taskRequestData;
    private TaskData taskData;
    private LocationData locationData;

    public TaskDetailsPresenter(TaskDetailsContract.View view, Context context) {
        this.view = view;
        this.taskRequestData = new TaskRequestData(context);
        this.taskData = new TaskData(context);
        this.locationData = new LocationData();
    }

    @Override
    public void getTaskDetails(int taskId) {
        taskData.getTaskDetails(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<TaskDetailViewModel>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(TaskDetailViewModel task) {
                                view.updateTask(task);
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.notifyError("Error loading task.");
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
    }

    @Override
    public void createTaskRequest(int taskId) {
        taskRequestData.createTaskRequest(taskId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showDialogforLoading();
                    }

                    @Override
                    public void onNext(Boolean result) {
                        view.notifySuccessful("Request has been sent successfully");
                        view.dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.notifyError("Error ocurred when sending request. Please try again.");
                        e.printStackTrace();
                        view.dismissDialog();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getLatLng(String location) {
        locationData.getLatLng(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<LocationContract>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LocationContract location) {
                        view.updateMap(location.getLat(), location.getLng());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.notifyError("Error ocurred when sending request. Please try again.");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
