package prot3ct.workit.views.register.base;

import prot3ct.workit.base.BaseView;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void showListJobsActivity();
    }

    interface Presenter {
    }
}
