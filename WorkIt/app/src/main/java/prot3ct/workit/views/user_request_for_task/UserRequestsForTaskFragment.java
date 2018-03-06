package prot3ct.workit.views.user_request_for_task;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prot3ct.workit.R;
import prot3ct.workit.views.user_request_for_task.base.UserRequestsForTaskContract;


public class UserRequestsForTaskFragment extends Fragment implements UserRequestsForTaskContract.View {
    private UserRequestsForTaskContract.Presenter presenter;
    private Context context;


    public UserRequestsForTaskFragment() {
        // Required empty public constructor
    }

    public static UserRequestsForTaskFragment newInstance() {
        return new UserRequestsForTaskFragment();
    }

    @Override
    public void setPresenter(UserRequestsForTaskContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_requests_for_task, container, false);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

}
