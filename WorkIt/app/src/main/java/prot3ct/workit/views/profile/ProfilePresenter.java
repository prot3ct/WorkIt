package prot3ct.workit.views.profile;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import prot3ct.workit.data.remote.UserData;
import prot3ct.workit.view_models.ProfileDetailsViewModel;
import prot3ct.workit.views.profile.base.ProfileContract;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private UserData userData;

    public ProfilePresenter(ProfileContract.View view, Context context) {
        this.view = view;
        userData = new UserData(context);
    }

    @Override
    public void getProfileDetails(int userId) {
        userData.getProfileDetails(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<ProfileDetailsViewModel>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(ProfileDetailsViewModel profile) {
                                view.updateProfile(profile);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                view.notifyError("Error loading profile.");
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
    }
}