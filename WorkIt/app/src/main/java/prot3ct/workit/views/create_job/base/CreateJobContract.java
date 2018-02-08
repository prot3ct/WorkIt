package prot3ct.workit.views.create_job.base;

import prot3ct.workit.base.BaseView;
import prot3ct.workit.views.list_jobs.base.ListJobsContract;

public interface CreateJobContract {
    interface View extends BaseView<CreateJobContract.Presenter> {
        void showListJobsActivity();

        void notifySuccessful();

        void showDialogforLoading();

        void dismissDialog();

        void notifyError(String errorMessage);
    }

    interface Presenter {
        void createTask(String title, String startDate, String endDate,
                        String description, String country, String city, String address,
                        String reward, String minimalRating, String minimalJobsCompleted);
    }
}
