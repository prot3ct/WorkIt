package prot3ct.workit.views.list_jobs;

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
import prot3ct.workit.views.create_job.CreateJobActivity;
import prot3ct.workit.views.list_jobs.base.ListJobsContract;
import prot3ct.workit.views.login.LoginActivity;

public class ListJobsFragment extends Fragment implements ListJobsContract.View {
    private ListJobsContract.Presenter presenter;
    private Context context;

    private Button createJobButton;
    private Button logoutButton;
    private ListView listTaskView;

    ArrayAdapter<TaskContract> taskAdapter;

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
        View view = inflater.inflate(R.layout.fragment_list_jobs, container, false);

        this.createJobButton = (Button) view.findViewById(R.id.id_create_job_button);
        this.logoutButton = (Button) view.findViewById(R.id.id_logout_button);
        this.listTaskView = (ListView) view.findViewById(R.id.id_list_tasks_list_view);

        this.createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateJobActivity();
            }
        });

        this.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
                notifySuccessful("You have logged out successfully");
                showLoginActivity();
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
    public void showCreateJobActivity() {
        Intent intent = new Intent(this.context, CreateJobActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginActivity() {
        Intent intent = new Intent(this.context, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void notifySuccessful(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void setupTasksAdapter(final List<? extends TaskContract> tasks) {
        this.taskAdapter = new ArrayAdapter<TaskContract>(this.getContext(), -1, (List<TaskContract>) tasks) {
            @NonNull
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.single_task, parent, false);
                }

                TextView taskTitle = (TextView) view.findViewById(R.id.id_single_task_title_text_view);


                taskTitle.setText(tasks.get(position).getTitle());

                return view;
            }
        };

        this.listTaskView.setAdapter(taskAdapter);
    }
}
