package prot3ct.workit.views.list_jobs.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.models.base.TaskContract;

public interface ListJobsContract {
    interface View extends BaseView<Presenter> {
        void showCreateJobActivity();

        void showLoginActivity();

        void notifySuccessful(String message);

        void setupTasksAdapter(final List<? extends TaskContract> tasks);
    }

    interface Presenter {
        void logout();

        void getAllTasks();
    }
}
