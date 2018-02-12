package prot3ct.workit.views.task_request_details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import prot3ct.workit.R;
import prot3ct.workit.views.task_request_details.base.TaskRequestDetailsContract;


public class TaskRequestDetailsFragment extends Fragment implements TaskRequestDetailsContract.View {
    private TaskRequestDetailsContract.Presenter presenter;
    private Context context;

    private TextView taskTitle;
    private EditText taskRequestDescription;

    public TaskRequestDetailsFragment() {
        // Required empty public constructor
    }

    public static TaskRequestDetailsFragment newInstance() {
        return new TaskRequestDetailsFragment();
    }

    @Override
    public void setPresenter(TaskRequestDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_request_details, container, false);

        this.taskTitle = view.findViewById(R.id.id_task_request_title_text_view);
        this.taskRequestDescription = view.findViewById(R.id.id_task_request_description_edit_text);
        int taskRequestId = this.getActivity().getIntent().getIntExtra("taskRequestId", 0);

        this.presenter.getTaskRequestById(taskRequestId);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void updateView(String title, String description) {
        this.taskTitle.setText(title);
        this.taskRequestDescription.setText(description);
    }
}
