package prot3ct.workit.views.navigation;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import prot3ct.workit.R;
import prot3ct.workit.views.completed_tasks.CompletedTasksActivity;
import prot3ct.workit.views.list_jobs.ListJobsActivity;
import prot3ct.workit.views.login.LoginActivity;
import prot3ct.workit.views.my_tasks.MyTasksActivity;

public class DrawerUtil {
    private final Activity activity;
    private final Toolbar toolbar;

    public DrawerUtil(Activity activity, Toolbar toolbar) {
        this.activity = activity;
        this.toolbar = toolbar;
    }

    public void getDrawer() {
        AccountHeader headerResult = new AccountHeaderBuilder()
            .withActivity(activity)
            .withHeaderBackground(R.drawable.navigation_background)
            .withTextColor(activity.getResources().getColor(R.color.md_black_1000))
            .addProfiles(
                    new ProfileDrawerItem().withName("Georgi Karaboychev").withEmail("prot3ctore@gmail.com").withIcon(activity.getResources().getDrawable(R.drawable.blank_profile_picture))
            )
            .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                @Override
                public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                    return false;
                }
            })
            .build();

        new DrawerBuilder()
            .withActivity(activity)
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
    }

    private PrimaryDrawerItem createBrowseTasksDrawerItem() {
        return new PrimaryDrawerItem()
            .withIdentifier(0)
            .withName("Browse tasks")
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(activity.getBaseContext(), ListJobsActivity.class);
                    activity.startActivity(intent);
                return true;
                }
            });
    }

    private PrimaryDrawerItem createMyTasksDrawerItem() {
        return new PrimaryDrawerItem()
            .withIdentifier(1)
            .withName("My tasks")
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(activity.getBaseContext(), MyTasksActivity.class);
                activity.startActivity(intent);
                return true;
                }
            });
    }

    private PrimaryDrawerItem createMyCompletedTasksDrawerItem() {
        return new PrimaryDrawerItem()
            .withIdentifier(2)
            .withName("Completed tasks")
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(activity.getBaseContext(), CompletedTasksActivity.class);
                    activity.startActivity(intent);
                return true;
                }
            });
    }

    private PrimaryDrawerItem createAssignedTasksDrawerItem() {
        return new PrimaryDrawerItem()
            .withIdentifier(3)
            .withName("Assigned to me tasks")
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(activity.getBaseContext(), LoginActivity.class);
                    activity.startActivity(intent);
                return true;
            }
        });
    }

    private PrimaryDrawerItem createProfileDrawerItem() {
        return new PrimaryDrawerItem()
            .withIdentifier(4)
            .withName("My profile")
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(activity.getBaseContext(), LoginActivity.class);
                    activity.startActivity(intent);
                return true;
                }
            });
    }

    private PrimaryDrawerItem createSettingsDrawerItem() {
        return new PrimaryDrawerItem()
            .withIdentifier(5)
            .withName("Settings")
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(activity.getBaseContext(), LoginActivity.class);
                    activity.startActivity(intent);
                return true;
                }
            });
    }

    private PrimaryDrawerItem createLogoutDrawerItem() {
        return new PrimaryDrawerItem()
            .withIdentifier(6)
            .withName("Logout Me")
            .withTextColor(activity.getResources().getColor(R.color.md_red_900))
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    Intent intent = new Intent(activity.getBaseContext(), LoginActivity.class);
                    activity.startActivity(intent);
                    return true;
                }
            });
    }
}
