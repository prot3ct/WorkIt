package prot3ct.workit.views.list_task_requests.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.view_models.TaskRequestListViewModel;

public interface ListTaskRequestContract {
    interface View extends BaseView<Presenter> {
        void setupTaskRequestsAdapter(List<TaskRequestListViewModel> users);

        void notifySuccessful(String message);

        void notifyError(String errorMessage);
    }

    interface Presenter {
        void getTaskRequests(int taskId);

        void updateTaskRequest(int taskRequestId, int taskRequestStatusId);
    }
}
