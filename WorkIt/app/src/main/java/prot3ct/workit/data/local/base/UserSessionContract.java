package prot3ct.workit.data.local.base;

public interface UserSessionContract {
    String getEmail();

    void setEmail(String username);

    void setId(int id);

    int getId();

    boolean isUserLoggedIn();

    void clearSession();
}