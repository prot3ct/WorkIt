package prot3ct.workit.views.list_tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import prot3ct.workit.R;
import prot3ct.workit.view_models.AvailableTasksListViewModel;
import prot3ct.workit.views.create_task.CreateTaskActivity;
import prot3ct.workit.views.list_tasks.base.ListTasksContract;
import prot3ct.workit.views.login.LoginActivity;

public class ListTasksFragment extends Fragment implements ListTasksContract.View {
    private ListTasksContract.Presenter presenter;
    private Context context;

    private ListTasksAdapter adapter;
    private FloatingActionButton createTaskButton;
    private RecyclerView recyclerTaskView;

    public ListTasksFragment() {
        // Required empty public constructor
    }

    public static ListTasksFragment newInstance() {
        return new ListTasksFragment();
    }

    @Override
    public void setPresenter(ListTasksContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_tasks, container, false);

        this.createTaskButton = view.findViewById(R.id.id_create_task_button);
        this.recyclerTaskView = view.findViewById(R.id.id_list_tasks_list_view);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerTaskView.setLayoutManager(llm);

        this.createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateJobActivity();
            }
        });

        presenter.getAllTasks();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void showCreateJobActivity() {
        Intent intent = new Intent(this.context, CreateTaskActivity.class);
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

    @Override
    public void setupTasksAdapter(final List<AvailableTasksListViewModel> tasks) {
        adapter = new ListTasksAdapter(tasks, context);
        recyclerTaskView.setAdapter(adapter);
    }

    @Override
    public void filterTask(String query) {
        adapter.filter(query);
    }
}
