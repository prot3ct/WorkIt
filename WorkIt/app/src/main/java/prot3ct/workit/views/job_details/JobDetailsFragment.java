package prot3ct.workit.views.job_details;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import prot3ct.workit.R;
import prot3ct.workit.models.base.TaskContract;
import prot3ct.workit.views.job_details.base.JobDetailsContract;

public class JobDetailsFragment extends Fragment implements JobDetailsContract.View {
    private JobDetailsContract.Presenter presenter;
    private Context context;

    private TextView taskTitle;
    private TextView taskDescription;
    private TextView taskStartDate;
    private TextView taskEndDate;

    public JobDetailsFragment() {
        // Required empty public constructor
    }

    public static JobDetailsFragment newInstance() {
        return new JobDetailsFragment();
    }

    @Override
    public void setPresenter(JobDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_details, container, false);

        this.taskTitle = view.findViewById(R.id.id_title_details_edit_text);
        this.taskDescription = view.findViewById(R.id.id_description_details_edit_text);
        this.taskStartDate = view.findViewById(R.id.id_start_time_details_edit_text);
        this.taskEndDate = view.findViewById(R.id.id_end_time_details_edit_text);

        TaskContract taskDetails = (TaskContract) this.getActivity().getIntent().getSerializableExtra("TaskDetails");

        this.taskTitle.setText(taskDetails.getTitle());
        this.taskDescription.setText(taskDetails.getDescription());
        this.taskStartDate.setText(taskDetails.getStartDate());
        this.taskEndDate.setText(taskDetails.getEndDate());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }
}
