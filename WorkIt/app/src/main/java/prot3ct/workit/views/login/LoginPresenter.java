package prot3ct.workit.views.login;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.views.login.base.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private UserData userData;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.view = view;
        userData = new UserData(context);
    }

    @Override
    public void loginUser(String email, String password) {
        userData.login(email, password)
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
                        view.dismissDialog();
                        view.showListJobsActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.notifyError("Error ocurred when logining in. Please try again.");
                        view.dismissDialog();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}