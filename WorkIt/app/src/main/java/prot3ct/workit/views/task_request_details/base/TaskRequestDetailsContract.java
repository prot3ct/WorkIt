package prot3ct.workit.views.task_request_details.base;

import prot3ct.workit.base.BaseView;

public interface TaskRequestDetailsContract {
    interface View extends BaseView<Presenter> {
        void updateView(String title, String description);
    }

    interface Presenter {
        void getTaskRequestById(int taskRequestId);
    }
}
