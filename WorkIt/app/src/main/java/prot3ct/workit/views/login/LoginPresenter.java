package prot3ct.workit.views.login;

import prot3ct.workit.views.login.base.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }
}