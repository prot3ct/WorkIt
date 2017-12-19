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
    public String getUsername() {
        return sharedPreferences.getString("username", null);
    }

    @Override
    public void setUsername(String username) {
        sharedPreferences.edit().putString("username", username).apply();
    }
}
