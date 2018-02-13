package prot3ct.workit.views.task_request_details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import prot3ct.workit.R;
import prot3ct.workit.utils.WorkItProgressDialog;
import prot3ct.workit.views.task_request_details.base.TaskRequestDetailsContract;


public class TaskRequestDetailsFragment extends Fragment implements TaskRequestDetailsContract.View {
    private TaskRequestDetailsContract.Presenter presenter;
    private Context context;

    private TextView taskTitle;
    private EditText taskRequestDescription;
    private EditText taskRequestCommentEditText;
    private Button taskRequestCommentButton;

    private WorkItProgressDialog dialog;

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

        this.dialog = new WorkItProgressDialog(context);
        this.taskTitle = view.findViewById(R.id.id_task_request_details_title_text_view);
        this.taskRequestDescription = view.findViewById(R.id.id_task_request_details_description_edit_text);
        this.taskRequestCommentEditText = view.findViewById(R.id.id_task_request_comment_edit_text);
        this.taskRequestCommentButton = view.findViewById(R.id.id_task_request_create_comment_button);
        final int taskRequestId = this.getActivity().getIntent().getIntExtra("taskRequestId", 0);
        this.presenter.getTaskRequestById(taskRequestId);

        this.taskRequestCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createTaskRequestComment(taskRequestId, taskRequestCommentEditText.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void showDialogforLoading() {
        this.dialog.showProgress("Logging in...");
    }

    @Override
    public void dismissDialog() {
        this.dialog.dismissProgress();
    }

    @Override
    public void notifySuccessful(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateView(String title, String description) {
        this.taskTitle.setText(title);
        this.taskRequestDescription.setText(description);
    }
}
