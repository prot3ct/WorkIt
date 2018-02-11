package prot3ct.workit.views.list_pending_requests.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.data.remote.result_models.TaskRequestResult;
import prot3ct.workit.models.base.TaskContract;

public interface ListPendingRequestsContract {
    interface View extends BaseView<Presenter> {
        void setupTasksAdapter(final List<? extends TaskRequestResult> taskRequests);
    }

    interface Presenter {
        void getTaskRequestsForCurrentUser();
    }
}
