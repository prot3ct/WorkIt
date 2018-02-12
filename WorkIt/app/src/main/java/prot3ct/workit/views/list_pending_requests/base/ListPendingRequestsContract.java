package prot3ct.workit.views.list_pending_requests.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.data.remote.result_models.TaskRequestListViewModel;

public interface ListPendingRequestsContract {
    interface View extends BaseView<Presenter> {
        void setupTasksAdapter(final List<? extends TaskRequestListViewModel> taskRequests);
    }

    interface Presenter {
        void getTaskRequestsForCurrentUser();
    }
}
