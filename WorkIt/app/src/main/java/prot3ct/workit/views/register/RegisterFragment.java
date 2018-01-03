package prot3ct.workit.views.register;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import prot3ct.workit.R;
import prot3ct.workit.views.list_jobs.ListJobsActivity;
import prot3ct.workit.views.register.base.RegisterContract;

public class RegisterFragment extends Fragment implements RegisterContract.View {
    private RegisterContract.Presenter presenter;
    private Context context;

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

        this.registerButton = view.findViewById(R.id.id_register_button);

        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListJobsActivity();
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
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

}