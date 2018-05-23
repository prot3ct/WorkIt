package prot3ct.workit.views.list_dialogs.base;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.view_models.TaskDetailViewModel;

public interface ListDialogsContract {
    interface View extends BaseView<ListDialogsContract.Presenter> {
        void showListJobsActivity();

        void updateTask(TaskDetailViewModel task);

        void notifySuccessful();

        void showDialogforLoading();

        void dismissDialog();

        void notifyError(String errorMessage);
    }

    interface Presenter {
        void updateTask(int taskId, String title, String startDate, String length,
                        String description, String city, String address, String reward);

        void getTaskDetails(int taskId);
    }
}
