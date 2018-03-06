package prot3ct.workit.views.task_request_details.base;

import java.util.List;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.data.remote.result_models.TaskRequestListCommentsViewModel;

public interface TaskRequestDetailsContract {
    interface View extends BaseView<Presenter> {
        void updateView(String title, String description);

        void showDialogforLoading();

        void dismissDialog();

        void notifySuccessful(String message);

        void notifyError(String message);

        void setupComments();

        void setupTaskRequestsAdapter(final List<? extends TaskRequestListCommentsViewModel> taskRequestComments);
    }

    interface Presenter {
        void getTaskRequestById(int taskRequestId);

        void createTaskRequestComment(int taskRequestId, String body);

        void getTaskRequestComments(int taskRequestId);

        void updateTaskRequest(int taskRequestId, int status);
    }
}
