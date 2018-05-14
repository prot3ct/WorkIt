package prot3ct.workit.views.my_tasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import prot3ct.workit.R;
import prot3ct.workit.views.my_tasks.base.MyTasksContract;
import prot3ct.workit.views.navigation.DrawerUtil;

public class MyTasksActivity extends AppCompatActivity {
    private MyTasksContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);
        Toolbar toolbar = this.findViewById(R.id.id_drawer_toolbar);
        setSupportActionBar(toolbar);
        setTitle("My Tasks");
        DrawerUtil drawer = new DrawerUtil(this, toolbar);
        drawer.getDrawer();

        MyTasksFragment myTasksFragment =
                (MyTasksFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (myTasksFragment == null) {
            myTasksFragment = MyTasksFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, myTasksFragment)
                    .commit();
        }

        this.presenter = new MyTasksPresenter(myTasksFragment, this);
        myTasksFragment.setPresenter(this.presenter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);

        return true;
    }
}
