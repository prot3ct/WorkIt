package prot3ct.workit.views.list_jobs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prot3ct.workit.R;
import prot3ct.workit.views.list_jobs.base.ListJobsContract;

public class ListJobsFragment extends Fragment implements ListJobsContract.View {
    private ListJobsContract.Presenter presenter;

    public ListJobsFragment() {
        // Required empty public constructor
    }

    public static ListJobsFragment newInstance() {
        return new ListJobsFragment();
    }

    @Override
    public void setPresenter(ListJobsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_jobs, container, false);

    }
}
