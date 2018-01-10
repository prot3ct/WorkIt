package prot3ct.workit.data.remote.base;

import io.reactivex.Observable;
import prot3ct.workit.models.base.UserContract;

public interface UserDataContract {
    Observable<Boolean> login(String username, String password);

    Observable<Boolean> register(String email, String firstname, String lastname, String password);

    void logoutUser();

    boolean isLoggedIn();
}
