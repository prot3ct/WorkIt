package prot3ct.workit.views.list_tasks.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.view_models.AvailableTasksListViewModel;

public interface ListTasksContract {
    interface View extends BaseView<Presenter> {
        void showCreateJobActivity();

        void showLoginActivity();

        void notifySuccessful(String message);

        void filterTask(String query);

        void setupTasksAdapter(final List<AvailableTasksListViewModel> tasks);
    }

    interface Presenter {
        void getAllTasks();
    }
}
