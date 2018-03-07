package prot3ct.workit.views.completed_tasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import prot3ct.workit.R;
import prot3ct.workit.views.completed_tasks.base.CompletedTasksContract;

public class CompletedTasksActivity extends AppCompatActivity {
    private CompletedTasksContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jobs);

        CompletedTasksFragment completedTasksFragment =
                (CompletedTasksFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (completedTasksFragment == null) {
            completedTasksFragment = CompletedTasksFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, completedTasksFragment)
                    .commit();
        }

        this.presenter = new CompletedTasksPresenter(completedTasksFragment, this);
        completedTasksFragment.setPresenter(this.presenter);
    }
}
