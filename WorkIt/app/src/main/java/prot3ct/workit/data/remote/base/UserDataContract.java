package prot3ct.workit.data.remote.base;

import io.reactivex.Observable;
import prot3ct.workit.models.base.UserContract;

public interface UserDataContract {
    Observable<Boolean> login(String username, String password);

    Observable<Boolean> register(String email, String fullName, String password);

    Observable<Boolean> createRaiting(int receiverUserId, int taskId, int receiverUserRoleId, String value);

    void logoutUser();

    boolean isLoggedIn();
}
