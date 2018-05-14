package prot3ct.workit.views.edit_task.base;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.data.remote.result_models.EditTaskViewModel;

public interface EditTaskContract {
    interface View extends BaseView<EditTaskContract.Presenter> {
        void showListJobsActivity();

        void updateTask(EditTaskViewModel task);

        void notifySuccessful();

        void showDialogforLoading();

        void dismissDialog();

        void notifyError(String errorMessage);
    }

    interface Presenter {
        void updateTask(int taskId, String title, String startDate, String length,
                        String description, String city, String address,
                        String reward, String minimalRating);

        void getTaskById(int taskId);
    }
}
