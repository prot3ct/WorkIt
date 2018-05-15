package prot3ct.workit.views.completed_tasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.stepstone.apprating.listener.RatingDialogListener;

import prot3ct.workit.R;
import prot3ct.workit.views.completed_tasks.base.CompletedTasksContract;
import prot3ct.workit.views.navigation.DrawerUtil;

public class CompletedTasksActivity extends AppCompatActivity implements RatingDialogListener {
    private CompletedTasksContract.Presenter presenter;
    private CompletedTasksFragment completedTasksFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);
        Toolbar toolbar = this.findViewById(R.id.id_drawer_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Browse Tasks");
        DrawerUtil drawer = new DrawerUtil(this, toolbar);
        drawer.getDrawer();

        completedTasksFragment =
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

    @Override
    public void onPositiveButtonClicked(int value, String description) {
        completedTasksFragment.postRaiting(value, description);
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }
}
