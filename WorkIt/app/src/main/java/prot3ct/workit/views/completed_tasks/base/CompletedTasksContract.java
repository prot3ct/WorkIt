package prot3ct.workit.views.completed_tasks.base;

import android.widget.Toast;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.models.base.TaskContract;

public interface CompletedTasksContract {
    interface View extends BaseView<Presenter> {
        void showDialogForLoading();

        void dismissDialog();

        void setupTasksAdapter(final List<? extends TaskContract> tasks);

        void notifyError(String msg);

        void notifySuccessful(String msg);
    }

    interface Presenter {
        void getMyCompletedTasks();

        void createRating(int receiverUserId, int taskId, int receiverUserRoleId, String value);
    }
}
