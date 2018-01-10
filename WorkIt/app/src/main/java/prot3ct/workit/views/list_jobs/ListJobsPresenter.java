package prot3ct.workit.views.list_jobs;

import android.content.Context;

import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.views.list_jobs.base.ListJobsContract;

public class ListJobsPresenter implements ListJobsContract.Presenter {
    private ListJobsContract.View view;
    private UserData userData;

    public ListJobsPresenter(ListJobsContract.View view, Context context) {
        this.view = view;
        this.userData = new UserData(context);
    }

    @Override
    public void logout() {
        this.userData.logoutUser();
    }
}
