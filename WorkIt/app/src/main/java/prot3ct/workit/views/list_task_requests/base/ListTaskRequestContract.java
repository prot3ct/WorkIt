package prot3ct.workit.views.list_task_requests.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.data.remote.result_models.TaskRequestListViewModel;

public interface ListTaskRequestContract {
    interface View extends BaseView<Presenter> {
        void setupTaskRequestsAdapter(final List<? extends TaskRequestListViewModel> taskRequests);
    }

    interface Presenter {
        void getTaskRequestsForTask(int taskId);
    }
}
