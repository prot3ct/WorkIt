package prot3ct.workit.views.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import prot3ct.workit.R;
import prot3ct.workit.views.list_jobs.ListJobsActivity;
import prot3ct.workit.views.list_pending_requests.ListPendingRequestsActivity;


public class DrawerFragment extends Fragment {

    private View view;

    public DrawerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        this.view = rootView;

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        this.setupDrawer();
    }

    protected void setupDrawer() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.id_drawer_toolbar);

        new DrawerBuilder()
                .withActivity(getActivity())
                .withToolbar(toolbar)
                .addDrawerItems(
                        this.createPendingRequestDraweerItem()
                )
                .build();
    }

    private PrimaryDrawerItem createPendingRequestDraweerItem() {
        return new PrimaryDrawerItem()
            .withIdentifier(0)
            .withName("My pending requests")
            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    Intent intent = new Intent(getContext(), ListPendingRequestsActivity.class);
                    startActivity(intent);
                    return true;
                }
            });
    }
}
