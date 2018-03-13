package prot3ct.workit.views.completed_tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import prot3ct.workit.views.list_jobs.RVAdapter;
import prot3ct.workit.views.login.LoginActivity;

public class CompletedTasksFragment extends Fragment implements CompletedTasksContract.View {
    private CompletedTasksContract.Presenter presenter;
    private Context context;

    private RateTaskDialog rateTaskDialog;
    private FloatingActionButton createTaskButton;
    private WorkItProgressDialog dialog;

    private RecyclerView recyclerTaskView;

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
        this.recyclerTaskView = view.findViewById(R.id.id_list_tasks_list_view);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerTaskView.setLayoutManager(llm);
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
        CompletedTaskAdapter adapter = new CompletedTaskAdapter(tasks, context);
        recyclerTaskView.setAdapter(adapter);
    }
}
