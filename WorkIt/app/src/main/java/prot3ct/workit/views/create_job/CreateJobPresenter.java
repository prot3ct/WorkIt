package prot3ct.workit.views.create_job;

import prot3ct.workit.views.create_job.base.CreateJobContract;

public class CreateJobPresenter implements CreateJobContract.Presenter {
    private CreateJobContract.View view;

    public CreateJobPresenter(CreateJobContract.View view) {
        this.view = view;
    }
}
