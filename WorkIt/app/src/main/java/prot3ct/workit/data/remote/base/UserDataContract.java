package prot3ct.workit.data.remote.base;

import io.reactivex.Observable;
import prot3ct.workit.view_models.ProfileDetailsViewModel;

public interface UserDataContract {
    Observable<ProfileDetailsViewModel> getProfileDetails();
}
