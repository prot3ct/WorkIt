package prot3ct.workit.views.job_details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import prot3ct.workit.R;
import prot3ct.workit.views.job_details.base.JobDetailsContract;

public class JobDetailsActivity extends AppCompatActivity {
    private JobDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        JobDetailsFragment jobDetailsFragment =
                (JobDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (jobDetailsFragment == null) {
            jobDetailsFragment = JobDetailsFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, jobDetailsFragment)
                    .commit();
        }

        this.presenter = new JobDetailsPresenter(jobDetailsFragment, this);
        jobDetailsFragment.setPresenter(this.presenter);
    }
}
