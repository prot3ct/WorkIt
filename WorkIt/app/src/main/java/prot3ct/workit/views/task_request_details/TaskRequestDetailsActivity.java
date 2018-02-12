package prot3ct.workit.views.task_request_details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import prot3ct.workit.R;
import prot3ct.workit.views.task_request_details.base.TaskRequestDetailsContract;

public class TaskRequestDetailsActivity extends AppCompatActivity {
    private TaskRequestDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pending_requests);

        TaskRequestDetailsFragment taskRequestDetailsFragment =
                (TaskRequestDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (taskRequestDetailsFragment == null) {
            taskRequestDetailsFragment = TaskRequestDetailsFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, taskRequestDetailsFragment)
                    .commit();
        }

        this.presenter = new TaskRequestDetailsPresenter(taskRequestDetailsFragment, this);
        taskRequestDetailsFragment.setPresenter(this.presenter);
    }
}
