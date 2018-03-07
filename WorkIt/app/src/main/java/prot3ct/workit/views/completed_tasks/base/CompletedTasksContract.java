package prot3ct.workit.views.completed_tasks.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.models.base.TaskContract;

public interface CompletedTasksContract {
    interface View extends BaseView<Presenter> {
        void setupTasksAdapter(final List<? extends TaskContract> tasks);
    }

    interface Presenter {
        void getMyTasks();
    }
}
