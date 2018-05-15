package prot3ct.workit.data.remote.base;

import io.reactivex.Observable;

public interface UserDataContract {
    Observable<Boolean> login(String username, String password);

    Observable<Boolean> register(String email, String fullName, String password);

    int getLoggedInUserId();

    void logoutUser();

    boolean isLoggedIn();
}
