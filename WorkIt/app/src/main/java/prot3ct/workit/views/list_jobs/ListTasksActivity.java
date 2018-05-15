package prot3ct.workit.views.list_jobs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import prot3ct.workit.R;
import prot3ct.workit.views.list_jobs.base.ListTasksContract;
import prot3ct.workit.views.navigation.DrawerUtil;

public class ListTasksActivity extends AppCompatActivity {
    private ListTasksContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);
        Toolbar toolbar = this.findViewById(R.id.id_drawer_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Browse Tasks");
        DrawerUtil drawer = new DrawerUtil(this, toolbar);
        drawer.getDrawer();

        ListTasksFragment listJobsFragment =
                (ListTasksFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (listJobsFragment == null) {
            listJobsFragment = ListTasksFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, listJobsFragment)
                    .commit();
        }

        this.presenter = new ListTasksPresenter(listJobsFragment, this);
        listJobsFragment.setPresenter(this.presenter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);

        return true;
    }
}
