package prot3ct.workit.views.register;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import prot3ct.workit.R;
import prot3ct.workit.views.list_jobs.ListJobsActivity;
import prot3ct.workit.views.register.base.RegisterContract;

public class RegisterFragment extends Fragment implements RegisterContract.View {
    private RegisterContract.Presenter presenter;
    private Context context;

    private EditText emailEditText;
    private EditText firstnameEditText;
    private EditText lastnameEditText;
    private EditText passwordEditText;
    private Button registerButton;

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        this.registerButton = (Button) view.findViewById(R.id.id_register_button);
        this.emailEditText = (EditText) view.findViewById(R.id.id_email_edit_text);
        this.firstnameEditText = (EditText) view.findViewById(R.id.id_firstname_edit_text);
        this.lastnameEditText = (EditText) view.findViewById(R.id.id_lastname_edit_text);
        this.passwordEditText = (EditText) view.findViewById(R.id.id_password_edit_text);

        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerUser(
                    emailEditText.getText().toString(),
                    firstnameEditText.getText().toString(),
                    lastnameEditText.getText().toString(),
                    passwordEditText.getText().toString()
                );
            }
        });

        return view;
    }

    @Override
    public void showListJobsActivity() {
        Intent intent = new Intent(this.context, ListJobsActivity.class);
        startActivity(intent);
    }

    @Override
    public void notifySuccessful() {
        Toast.makeText(getContext(), "LOGINNNN", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

}