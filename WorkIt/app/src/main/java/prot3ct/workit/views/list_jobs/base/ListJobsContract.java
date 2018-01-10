package prot3ct.workit.views.list_jobs.base;

import prot3ct.workit.base.BaseView;

public interface ListJobsContract {
    interface View extends BaseView<Presenter> {
        void showCreateJobActivity();

        void showLoginActivity();

        void notifySuccessful(String message);
    }

    interface Presenter {
        void logout();
    }
}
