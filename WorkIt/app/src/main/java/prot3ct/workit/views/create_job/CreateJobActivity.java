package prot3ct.workit.views.create_job;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import prot3ct.workit.R;
import prot3ct.workit.views.create_job.base.CreateJobContract;

public class CreateJobActivity extends AppCompatActivity {
    private CreateJobContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create task");
        setContentView(R.layout.activity_create_job);

        CreateJobFragment createJobFragment =
                (CreateJobFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (createJobFragment == null) {
            createJobFragment = CreateJobFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, createJobFragment)
                    .commit();
        }

        this.presenter = new CreateJobPresenter(createJobFragment, this);
        createJobFragment.setPresenter(this.presenter);
    }
}
