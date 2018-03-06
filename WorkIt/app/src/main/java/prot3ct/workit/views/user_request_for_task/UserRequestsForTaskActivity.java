package prot3ct.workit.views.user_request_for_task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import prot3ct.workit.R;
import prot3ct.workit.views.user_request_for_task.base.UserRequestsForTaskContract;

public class UserRequestsForTaskActivity extends AppCompatActivity {
    private UserRequestsForTaskContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_requests_for_task);

        UserRequestsForTaskFragment userRequestsForTaskFragment =
                (UserRequestsForTaskFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (userRequestsForTaskFragment == null) {
            userRequestsForTaskFragment = UserRequestsForTaskFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, userRequestsForTaskFragment)
                    .commit();
        }

        this.presenter = new UserRequestsForTaskPresenter(userRequestsForTaskFragment, this);
        userRequestsForTaskFragment.setPresenter(this.presenter);
    }
}
