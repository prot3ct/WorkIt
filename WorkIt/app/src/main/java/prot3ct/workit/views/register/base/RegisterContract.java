package prot3ct.workit.views.register.base;

import prot3ct.workit.base.BaseView;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void showListJobsActivity();

        void notifySuccessful();

        void notifyError(String message);
    }

    interface Presenter {
        void registerUser(String email, String firstname, String lastname, String password);
    }
}
