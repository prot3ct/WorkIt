package prot3ct.workit.views.job_details;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.TaskRequestData;
import prot3ct.workit.views.job_details.base.JobDetailsContract;

public class JobDetailsPresenter implements JobDetailsContract.Presenter {
    private JobDetailsContract.View view;
    private TaskRequestData taskRequestData;

    public JobDetailsPresenter(JobDetailsContract.View view, Context context) {
        this.view = view;
        this.taskRequestData = new TaskRequestData(context);
    }

    @Override
    public void createTaskRequest(String description, int taskId) {
        taskRequestData.createTaskRequest(description, taskId)
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
//                                redirect to requests view
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.notifyError("Error ocurred when sending request. Please try again.");
                        view.dismissDialog();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
