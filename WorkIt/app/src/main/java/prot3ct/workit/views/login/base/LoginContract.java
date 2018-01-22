package prot3ct.workit.views.login.base;

import prot3ct.workit.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showListJobsActivity();

        void showRegisterActivity();

        void notifyError(String errorMessage);

        void notifySuccessful();

        void showDialogforLoading();

        void dismissDialog();
    }

    interface Presenter {
        void loginUser(String email, String password);
    }
}
