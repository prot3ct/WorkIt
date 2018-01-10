package prot3ct.workit.views.list_jobs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import prot3ct.workit.R;
import prot3ct.workit.views.list_jobs.base.ListJobsContract;

public class ListJobsActivity extends AppCompatActivity {
    private ListJobsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jobs);

        ListJobsFragment listJobsFragment =
                (ListJobsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (listJobsFragment == null) {
            listJobsFragment = ListJobsFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, listJobsFragment)
                    .commit();
        }

        this.presenter = new ListJobsPresenter(listJobsFragment, this);
        listJobsFragment.setPresenter(this.presenter);
    }
}
