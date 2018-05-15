package prot3ct.workit.views.my_tasks.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.view_models.MyTasksListViewModel;

public interface MyTasksContract {
    interface View extends BaseView<Presenter> {
        void showCreateJobActivity();

        void notifySuccessful(String message);

        void notifyError(String errorMessage);

        void setupTasksAdapter(final List<MyTasksListViewModel> tasks);
    }

    interface Presenter {
        void getMyTasks();
    }
}
