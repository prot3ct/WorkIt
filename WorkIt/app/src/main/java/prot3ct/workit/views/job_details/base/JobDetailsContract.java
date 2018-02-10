package prot3ct.workit.views.job_details.base;

import prot3ct.workit.base.BaseView;

public interface JobDetailsContract {
    interface View extends BaseView<Presenter> {
        void notifySuccessful(String message);

        void notifyError(String errorMessage);

        void showDialogforLoading();

        void dismissDialog();
    }

    interface Presenter {
        void createTaskRequest(String description, int taskId);
    }
}
