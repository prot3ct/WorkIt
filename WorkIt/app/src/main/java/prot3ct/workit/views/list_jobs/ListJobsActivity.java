package prot3ct.workit.views.list_jobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import prot3ct.workit.R;
import prot3ct.workit.views.completed_tasks.CompletedTasksActivity;
import prot3ct.workit.views.list_jobs.base.ListJobsContract;
import prot3ct.workit.views.login.LoginActivity;

public class ListJobsActivity extends AppCompatActivity {
    private ListJobsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jobs);
        Toolbar toolbar = this.findViewById(R.id.id_drawer_toolbar);
        toolbar.setTitle("Browse tasks");
        setSupportActionBar(toolbar);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.navigation_background)
                .withTextColor(getResources().getColor(R.color.md_black_1000))
                .addProfiles(
                        new ProfileDrawerItem().withName("Georgi Karaboychev").withEmail("prot3ctore@gmail.com").withIcon(getResources().getDrawable(R.drawable.blank_profile_picture))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        createBrowseTasksDrawerItem(),
                        createMyTasksDrawerItem(),
                        createMyCompletedTasksDrawerItem(),
                        createAssignedTasksDrawerItem(),
                        createProfileDrawerItem(),
                        createSettingsDrawerItem(),
                        createLogoutDrawerItem()
                )
                .build();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);

        return true;
    }

    private PrimaryDrawerItem createBrowseTasksDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(0)
                .withName("Browse tasks")
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(getBaseContext(), CompletedTasksActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
    }

    private PrimaryDrawerItem createMyTasksDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName("My tasks")
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(getBaseContext(), ListJobsActivity.class);
                        intent.putExtra("TYPE", "MyTasks");
                        startActivity(intent);
                        return true;
                    }
                });
    }

    private PrimaryDrawerItem createMyCompletedTasksDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName("Completed tasks")
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(getBaseContext(), CompletedTasksActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
    }

    private PrimaryDrawerItem createAssignedTasksDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName("Assigned to me tasks")
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
    }

    private PrimaryDrawerItem createProfileDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(4)
                .withName("My profile")
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
    }

    private PrimaryDrawerItem createSettingsDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(5)
                .withName("Settings")
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
    }

    private PrimaryDrawerItem createLogoutDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(6)
                .withName("Logout Me")
                .withTextColor(getResources().getColor(R.color.md_red_900))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
    }
}
