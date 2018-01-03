package prot3ct.workit.views.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.sql.BatchUpdateException;

import prot3ct.workit.R;
import prot3ct.workit.views.list_jobs.ListJobsActivity;
import prot3ct.workit.views.login.base.LoginContract;
import prot3ct.workit.views.register.RegisterActivity;

public class LoginFragment extends Fragment implements LoginContract.View {
    private LoginContract.Presenter presenter;
    private Context context;
    private Button loginButton;
    private Button registerButton;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_jobs, container, false);
        this.loginButton = view.findViewById(R.id.id_login_button);
        this.registerButton = view.findViewById(R.id.id_register_button);

        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListJobsActivity();
            }
        });

        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterActivity();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void showListJobsActivity() {
        Intent intent = new Intent(this.context, ListJobsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRegisterActivity() {
        Intent intent = new Intent(this.context, RegisterActivity.class);
        startActivity(intent);
    }
}