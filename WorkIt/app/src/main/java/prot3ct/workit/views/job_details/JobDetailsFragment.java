package prot3ct.workit.views.job_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import prot3ct.workit.R;
import prot3ct.workit.models.base.TaskContract;
import prot3ct.workit.utils.WorkItProgressDialog;
import prot3ct.workit.views.job_details.base.JobDetailsContract;
import prot3ct.workit.views.list_task_requests.ListTaskRequestsActivity;

public class JobDetailsFragment extends Fragment implements JobDetailsContract.View {
    private JobDetailsContract.Presenter presenter;
    private Context context;
    private TaskContract taskDetails;

    private TaskRequestDialog applyForTaskDialog;
    private TextView taskTitle;
    private TextView taskDescription;
    private TextView taskStartDate;
    private TextView taskEndDate;
    private Button applyForTaskButton;
    private Button taskersButton;
    private WorkItProgressDialog dialog;

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

        applyForTaskDialog = new TaskRequestDialog();
        applyForTaskDialog.setPresenter(this.presenter);
        this.dialog = new WorkItProgressDialog(context);
        this.taskersButton = view.findViewById(R.id.id_taskers_button);
        this.applyForTaskButton = view.findViewById(R.id.id_apply_for_task_button);
        this.taskTitle = view.findViewById(R.id.id_title_details_edit_text);
        this.taskDescription = view.findViewById(R.id.id_description_details_edit_text);
        this.taskStartDate = view.findViewById(R.id.id_start_time_details_edit_text);
        this.taskEndDate = view.findViewById(R.id.id_end_time_details_edit_text);

        this.taskDetails = (TaskContract) this.getActivity().getIntent().getSerializableExtra("TaskDetails");
        applyForTaskDialog.setTaskId(taskDetails.getId());

        this.taskTitle.setText(taskDetails.getTitle());
        this.taskDescription.setText(taskDetails.getDescription());
        this.taskStartDate.setText(taskDetails.getStartDate());
        this.taskEndDate.setText(taskDetails.getEndDate());

        this.applyForTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyForTaskDialog.show(getFragmentManager(), "text_popup");
            }
        });

        this.taskersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListTaskRequestsActivity.class);
                intent.putExtra("TaskId", taskDetails.getId());
                startActivity(intent);
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
    public void notifySuccessful(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDialogforLoading() {
        this.dialog.showProgress("Logging in...");
    }

    @Override
    public void dismissDialog() {
        this.dialog.dismissProgress();
    }
}
