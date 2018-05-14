package prot3ct.workit.views.my_tasks.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.models.base.TaskContract;

public interface MyTasksContract {
    interface View extends BaseView<Presenter> {
        void showCreateJobActivity();

        void notifySuccessful(String message);

        void setupTasksAdapter(final List<? extends TaskContract> tasks);
    }

    interface Presenter {
        void getMyTasks();
    }
}
