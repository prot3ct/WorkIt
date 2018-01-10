package prot3ct.workit.views.create_job;

import android.content.Context;

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
    public void createTask(String title, String startDate, String endDate,
                           String description, String country, String city, String address,
                           String reward, String minimalRating, String minimalJobsCompleted) {
        jobData.createTask(title, startDate, endDate, description, country,
                city, address, reward, minimalJobsCompleted, minimalJobsCompleted)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
//                        view.showDialogForLoading();
                            }

                            @Override
                            public void onNext(Boolean value) {
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
