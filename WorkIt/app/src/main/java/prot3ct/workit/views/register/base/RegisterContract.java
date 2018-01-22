package prot3ct.workit.views.register.base;

import prot3ct.workit.base.BaseView;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void showLoginActivity();

        void notifySuccessful();

        void notifyError(String message);

        void showDialogforLoading();

        void dismissDialog();
    }

    interface Presenter {
        void registerUser(String email, String firstname, String lastname, String password);
    }
}
