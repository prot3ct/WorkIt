package prot3ct.workit.views.task_request_details.base;

import prot3ct.workit.base.BaseView;

public interface TaskRequestDetailsContract {
    interface View extends BaseView<Presenter> {
        void updateView(String title, String description);

        void showDialogforLoading();

        void dismissDialog();

        void notifySuccessful(String message);

        void notifyError(String message);
    }

    interface Presenter {
        void getTaskRequestById(int taskRequestId);

        void createTaskRequestComment(int taskRequestId, String body);
    }
}
