package prot3ct.workit.views.create_job;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.JobData;
import prot3ct.workit.views.create_job.base.CreateJobContract;

public class CreateJobPresenter implements CreateJobContract.Presenter {
    private CreateJobContract.View view;
    private JobData jobData;

    public CreateJobPresenter(CreateJobContract.View view, Context context) {
        this.view = view;
        this.jobData = new JobData(context);
    }

    @Override
    public void createTask(String title, String startDate, String length,
                           String description, String city, String address,
                           String reward, String minimalRating) {
        jobData.createTask(title, startDate, length, description,
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
                                view.notifyError("Error ocurred when trying to create task. Please try again.");
                                view.dismissDialog();
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
    }
}
