package prot3ct.workit.views.list_jobs;

import prot3ct.workit.views.list_jobs.base.ListJobsContract;

public class ListJobsPresenter implements ListJobsContract.Presenter {
    private ListJobsContract.View view;

    public ListJobsPresenter(ListJobsContract.View view) {
        this.view = view;
    }
}
