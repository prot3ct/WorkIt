package prot3ct.workit.views.my_tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import prot3ct.workit.R;
import prot3ct.workit.models.base.TaskContract;
import prot3ct.workit.views.create_job.CreateJobActivity;
import prot3ct.workit.views.login.LoginActivity;
import prot3ct.workit.views.my_tasks.base.MyTasksContract;

public class MyTasksFragment extends Fragment implements MyTasksContract.View {
    private MyTasksContract.Presenter presenter;
    private Context context;

    private FloatingActionButton createTaskButton;
//    private Button logoutButton;
    private RecyclerView recyclerTaskView;

    public MyTasksFragment() {
        // Required empty public constructor
    }

    public static MyTasksFragment newInstance() {
        return new MyTasksFragment();
    }

    @Override
    public void setPresenter(MyTasksContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_tasks, container, false);

        this.createTaskButton = view.findViewById(R.id.id_create_task_button);
        this.recyclerTaskView = view.findViewById(R.id.id_my_tasks_list_view);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerTaskView.setLayoutManager(llm);

        this.createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateJobActivity();
            }
        });

        presenter.getMyTasks();

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
    public void notifySuccessful(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupTasksAdapter(final List<? extends TaskContract> tasks) {
        SingleTaskAdapter adapter = new SingleTaskAdapter(tasks, context);
        recyclerTaskView.setAdapter(adapter);
    }
}
