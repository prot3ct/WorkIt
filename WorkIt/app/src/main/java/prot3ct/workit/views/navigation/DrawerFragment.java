package prot3ct.workit.views.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DrawerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DrawerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawerFragment extends Fragment {

    private View view;

    public DrawerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);

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
                        this.createCameraDrawerItem(),
                )
                .build();
    }

//    private PrimaryDrawerItem createSearchDrawerItem() {
//        return new PrimaryDrawerItem()
//                .withIdentifier(R.integer.SearchDrawerItemIdentifier)
//                .withName(R.string.SearchDrawerItemName)
//                .withIcon(R.drawable.ic_search)
//                .withSelectedIcon(R.drawable.ic_search_active)
//                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
//                    Intent intent = new Intent(getContext(), SearchActivity.class);
//                    startActivity(intent);
//                    return true;
//                });
//    }

    private PrimaryDrawerItem createCameraDrawerItem() {
        return new PrimaryDrawerItem()
            .withIdentifier(0)
            .withName("My pending requests")
            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    Intent intent = new Intent(getContext(), ListJobsActivity.class);
                    startActivity(intent);
                    return true;
                }
            });
    }
}
