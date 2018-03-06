package prot3ct.workit.views.task_request_details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import prot3ct.workit.R;
import prot3ct.workit.data.remote.result_models.TaskRequestListCommentsViewModel;
import prot3ct.workit.utils.WorkItProgressDialog;
import prot3ct.workit.views.task_request_details.base.TaskRequestDetailsContract;


public class TaskRequestDetailsFragment extends Fragment implements TaskRequestDetailsContract.View {
    private TaskRequestDetailsContract.Presenter presenter;
    private Context context;

    private int taskRequestId;

    private TextView taskTitle;
    private EditText taskRequestDescription;
    private EditText taskRequestCommentEditText;
    private Button taskRequestCommentButton;
    private Button taskRequestAcceptButton;
    private Button taskRequestDeclineButton;
    private ListView taskRequestCommentsListView;
    ArrayAdapter<TaskRequestListCommentsViewModel> taskRequestCommentsAdapter;

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
        this.taskRequestAcceptButton = view.findViewById(R.id.id_task_request_accept_button);
        this.taskRequestDeclineButton = view.findViewById(R.id.id_task_request_decline_button);
        this.taskTitle = view.findViewById(R.id.id_task_request_details_title_text_view);
        this.taskRequestDescription = view.findViewById(R.id.id_task_request_details_description_edit_text);
        this.taskRequestCommentEditText = view.findViewById(R.id.id_task_request_comment_edit_text);
        this.taskRequestCommentButton = view.findViewById(R.id.id_task_request_create_comment_button);
        this.taskRequestCommentsListView = view.findViewById(R.id.id_task_request_details_comments_list_view);
        this.taskRequestId = this.getActivity().getIntent().getIntExtra("taskRequestId", 0);

        this.presenter.getTaskRequestById(taskRequestId);
        setupComments();

        this.taskRequestCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createTaskRequestComment(taskRequestId, taskRequestCommentEditText.getText().toString());
            }
        });

        this.taskRequestAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateTaskRequest(taskRequestId , 2);
            }
        });

        this.taskRequestDeclineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateTaskRequest(taskRequestId, 1);
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

    @Override
    public void setupComments() {
        this.presenter.getTaskRequestComments(taskRequestId);
    }

    @Override
    public void setupTaskRequestsAdapter(final List<? extends TaskRequestListCommentsViewModel> taskRequestComments) {
        this.taskRequestCommentsAdapter = new ArrayAdapter<TaskRequestListCommentsViewModel>(this.getContext(), -1, (List<TaskRequestListCommentsViewModel>) taskRequestComments) {
            @NonNull
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.single_task_request_comment, parent, false);
                }

                TextView taskRequestCommentBody = view.findViewById(R.id.id_task_request_comment_body_text_view);
                TextView taskRequestCommentUser = view.findViewById(R.id.id_task_request_comment_user_text_view);

                taskRequestCommentBody.setText(taskRequestComments.get(position).getBody());
                taskRequestCommentUser.setText("Commented by " + taskRequestComments.get(position).getName());

                return view;
            }
        };

        this.taskRequestCommentsListView.setAdapter(taskRequestCommentsAdapter);
    }
}
