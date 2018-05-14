package prot3ct.workit.views.edit_task;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.JobData;
import prot3ct.workit.data.remote.result_models.EditTaskViewModel;
import prot3ct.workit.views.edit_task.base.EditTaskContract;

public class EditTaskPresenter implements EditTaskContract.Presenter {
    private EditTaskContract.View view;
    private JobData jobData;

    public EditTaskPresenter(EditTaskContract.View view, Context context) {
        this.view = view;
        this.jobData = new JobData(context);
    }

    @Override
    public void updateTask(int taskId, String title, String startDate, String length,
                           String description, String city, String address,
                           String reward, String minimalRating) {
        jobData.updateTask(taskId, title, startDate, length, description,
            city, address, reward, minimalRating)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showDialogforLoading();
                    }

                    @Override
                    public void onNext(Boolean value) {
                        view.notifySuccessful();
                        view.showListJobsActivity();
                        view.dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.notifyError("Error ocurred when trying to update task. Please try again.");
                        e.printStackTrace();
                        view.dismissDialog();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getTaskById(int taskId) {
        jobData.getTaskById(taskId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<EditTaskViewModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(EditTaskViewModel task) {
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
}
