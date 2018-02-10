package prot3ct.workit.views.job_details;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import prot3ct.workit.R;
import prot3ct.workit.models.base.TaskContract;
import prot3ct.workit.utils.WorkItProgressDialog;
import prot3ct.workit.views.job_details.base.JobDetailsContract;

public class JobDetailsFragment extends Fragment implements JobDetailsContract.View {
    private JobDetailsContract.Presenter presenter;
    private Context context;
    private TaskContract taskDetails;

    private TaskRequestDialog applyForTaskWindow;
    private TextView taskTitle;
    private TextView taskDescription;
    private TextView taskStartDate;
    private TextView taskEndDate;
    private Button applyForTaskButton;
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

        applyForTaskWindow = new TaskRequestDialog(this.getActivity());
        this.dialog = new WorkItProgressDialog(context);
        this.applyForTaskButton = view.findViewById(R.id.id_apply_for_task_button);
        this.taskTitle = view.findViewById(R.id.id_title_details_edit_text);
        this.taskDescription = view.findViewById(R.id.id_description_details_edit_text);
        this.taskStartDate = view.findViewById(R.id.id_start_time_details_edit_text);
        this.taskEndDate = view.findViewById(R.id.id_end_time_details_edit_text);

        this.taskDetails = (TaskContract) this.getActivity().getIntent().getSerializableExtra("TaskDetails");

        this.taskTitle.setText(taskDetails.getTitle());
        this.taskDescription.setText(taskDetails.getDescription());
        this.taskStartDate.setText(taskDetails.getStartDate());
        this.taskEndDate.setText(taskDetails.getEndDate());

        this.applyForTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public class TaskRequestDialog extends Dialog {
        private JobDetailsPresenter presenter;

        private Activity activity;
        private Dialog dialog;
        private Button confirmButton;
        private Button cancelButton;
        private EditText description;

            public TaskRequestDialog(Activity activity) {
            super(activity);
            this.activity = activity;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_task_request);

            this.confirmButton = findViewById(R.id.id_task_request_confirm_button);
            this.cancelButton = findViewById(R.id.id_task_request_cancel_button);
            this.description = findViewById(R.id.id_task_request_description_edit_text);

            this.confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createRequest();
                }
            });
            this.cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelRequest();
                }
            });
        }

        private void createRequest() {
            presenter.createTaskRequest(this.description.getText().toString(), taskDetails.getId());
            activity.finish();
        }

        private void cancelRequest() {
            dismiss();
        }
    }
}
