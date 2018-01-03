package prot3ct.workit.views.create_job;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prot3ct.workit.R;
import prot3ct.workit.views.create_job.base.CreateJobContract;

public class CreateJobFragment extends Fragment implements CreateJobContract.View {
    private CreateJobContract.Presenter presenter;

    public CreateJobFragment() {
        // Required empty public constructor
    }

    public static CreateJobFragment newInstance() {
        return new CreateJobFragment();
    }

    @Override
    public void setPresenter(CreateJobContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_job, container, false);

    }
}
