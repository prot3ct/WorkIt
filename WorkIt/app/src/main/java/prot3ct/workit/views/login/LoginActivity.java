package prot3ct.workit.views.login;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import prot3ct.workit.R;
import prot3ct.workit.views.login.base.LoginContract;

public class LoginActivity extends AppCompatActivity {
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jobs);

        LoginFragment loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, loginFragment)
                    .commit();
        }

        this.presenter = new LoginPresenter(loginFragment);
        loginFragment.setPresenter(this.presenter);
    }
}