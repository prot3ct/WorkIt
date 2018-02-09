package prot3ct.workit.views.job_details;

import android.content.Context;

import prot3ct.workit.views.job_details.base.JobDetailsContract;

public class JobDetailsPresenter implements JobDetailsContract.Presenter {
    private JobDetailsContract.View view;

    public JobDetailsPresenter(JobDetailsContract.View view, Context context) {
        this.view = view;
    }
}
