package prot3ct.workit.views.completed_tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import prot3ct.workit.R;
import prot3ct.workit.models.base.TaskContract;
import prot3ct.workit.utils.WorkItProgressDialog;
import prot3ct.workit.views.completed_tasks.base.CompletedTasksContract;
import prot3ct.workit.views.create_job.CreateJobActivity;
import prot3ct.workit.views.job_details.JobDetailsActivity;
import prot3ct.workit.views.job_details.TaskRequestDialog;
import prot3ct.workit.views.login.LoginActivity;

public class CompletedTasksFragment extends Fragment implements CompletedTasksContract.View {
    private CompletedTasksContract.Presenter presenter;
    private Context context;

    private RateTaskDialog rateTaskDialog;
    private Button createJobButton;
    private ListView listTaskView;

    private WorkItProgressDialog dialog;

    ArrayAdapter<TaskContract> taskAdapter;

    public CompletedTasksFragment() {
        // Required empty public constructor
    }

    public static CompletedTasksFragment newInstance() {
        return new CompletedTasksFragment();
    }

    @Override
    public void setPresenter(CompletedTasksContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_jobs, container, false);

        this.dialog = new WorkItProgressDialog(context);
        this.rateTaskDialog = new RateTaskDialog();
        this.rateTaskDialog.setPresenter(this.presenter);
        this.createJobButton = (Button) view.findViewById(R.id.id_create_job_button);
        this.listTaskView = (ListView) view.findViewById(R.id.id_list_tasks_list_view);
        presenter.getMyCompletedTasks();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void notifyError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifySuccessful(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogForLoading() {
        this.dialog.showProgress("Loading...");
    }

    @Override
    public void dismissDialog() {
        this.dialog.dismissProgress();
    }

    @Override
    public void setupTasksAdapter(final List<? extends TaskContract> tasks) {
        this.taskAdapter = new ArrayAdapter<TaskContract>(this.getContext(), -1, (List<TaskContract>) tasks) {
            @NonNull
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.single_completed_task, parent, false);
                }

                TextView taskTitle = (TextView) view.findViewById(R.id.id_single_task_title_text_view);
                Button rateButton = view.findViewById(R.id.id_completed_task_rate_button);

                taskTitle.setText(tasks.get(position).getTitle());
                taskTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, JobDetailsActivity.class);
                        intent.putExtra("TaskDetails", tasks.get(position));
                        startActivity(intent);
                    }
                });
                rateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rateTaskDialog.setInfo(tasks.get(position).getId(), tasks.get(position).getAssignedUserId());
                        rateTaskDialog.show(getFragmentManager(), "rate_popup");
                    }
                });

                return view;
            }
        };

        this.listTaskView.setAdapter(taskAdapter);
    }
}
