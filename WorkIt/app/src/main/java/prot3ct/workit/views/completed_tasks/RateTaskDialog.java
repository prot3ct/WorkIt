package prot3ct.workit.views.completed_tasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import prot3ct.workit.R;
import prot3ct.workit.views.completed_tasks.base.CompletedTasksContract;
import prot3ct.workit.views.job_details.base.JobDetailsContract;

public class RateTaskDialog extends DialogFragment {
    private CompletedTasksContract.Presenter presenter;
    private View view;
    private EditText status;

    private int taskId;
    private int assignedUserId;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        this.view = inflater.inflate(R.layout.dialog_rate_task, null);

        this.status = view.findViewById(R.id.id_rate_task_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.createRating(assignedUserId, taskId, 1, status.getText().toString());
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return builder.create();
    }

    public void setInfo(int taskId, int assignedUserId) {
        this.taskId = taskId;
        this.assignedUserId = assignedUserId;
    }

    public void setPresenter(CompletedTasksContract.Presenter presenter) {
        this.presenter = presenter;
    }
}