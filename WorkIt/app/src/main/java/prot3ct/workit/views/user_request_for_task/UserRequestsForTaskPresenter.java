package prot3ct.workit.views.user_request_for_task;

import android.content.Context;

import prot3ct.workit.data.remote.TaskRequestData;
import prot3ct.workit.views.user_request_for_task.base.UserRequestsForTaskContract;

public class UserRequestsForTaskPresenter implements  UserRequestsForTaskContract.Presenter {
    private UserRequestsForTaskContract.View view;
    private TaskRequestData taskRequestData;

    public UserRequestsForTaskPresenter(UserRequestsForTaskContract.View view, Context context) {
        this.view = view;
        this.taskRequestData = new TaskRequestData(context);
    }

}
