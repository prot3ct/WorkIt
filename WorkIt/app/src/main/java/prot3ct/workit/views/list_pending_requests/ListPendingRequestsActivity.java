package prot3ct.workit.views.list_pending_requests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import prot3ct.workit.R;
import prot3ct.workit.views.list_jobs.ListJobsFragment;
import prot3ct.workit.views.list_jobs.ListJobsPresenter;
import prot3ct.workit.views.list_jobs.base.ListJobsContract;
import prot3ct.workit.views.list_pending_requests.base.ListPendingRequestsContract;

public class ListPendingRequestsActivity extends AppCompatActivity {
    private ListPendingRequestsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pending_requests);

        ListPendingRequestsFragment listPendingRequestsFragment =
                (ListPendingRequestsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (listPendingRequestsFragment == null) {
            listPendingRequestsFragment = ListPendingRequestsFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, listPendingRequestsFragment)
                    .commit();
        }

        this.presenter = new ListPendingRequestsPresenter(listPendingRequestsFragment, this);
        listPendingRequestsFragment.setPresenter(this.presenter);
    }
}
