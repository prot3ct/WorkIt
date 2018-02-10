package prot3ct.workit.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import prot3ct.workit.data.local.base.UserSessionContract;

public class UserSession implements UserSessionContract{
    private final SharedPreferences sharedPreferences;

    public UserSession(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public String getEmail() {
        return sharedPreferences.getString("username", null);
    }

    @Override
    public int getId() {
        return sharedPreferences.getInt("id", 0);
    }

    @Override
    public void setEmail(String username) {
        sharedPreferences.edit().putString("username", username).apply();
    }

    @Override
    public void setId(int id) {
        sharedPreferences.edit().putInt("id", id).apply();
    }

    @Override
    public boolean isUserLoggedIn() {
        String username = getEmail();
        return username != null;
    }

    @Override
    public void clearSession() {
        setEmail(null);
    }
}
