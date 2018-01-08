package prot3ct.workit.views.register;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.models.base.UserContract;
import prot3ct.workit.views.register.base.RegisterContract;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private UserData userData;

    public RegisterPresenter(RegisterContract.View view, Context context) {
        this.view = view;
        userData = new UserData(context);
    }

    @Override
    public void registerUser(String email, String firstname, String lastname, String password) {
        userData.register(email, firstname, lastname, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                new Observer<UserContract>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        view.showDialogForLoading();
                    }

                    @Override
                    public void onNext(UserContract value) {
                        view.notifySuccessful();
                        view.showListJobsActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("CEKOOOO", e.getMessage());
                        view.notifyError("Error ocurred when registering. Please try again later.");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}