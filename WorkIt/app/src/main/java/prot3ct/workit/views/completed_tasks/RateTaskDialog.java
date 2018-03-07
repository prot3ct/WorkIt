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
    private int taskId;
    private View view;
    private EditText description;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        this.view = inflater.inflate(R.layout.dialog_task_request, null);

        this.description = view.findViewById(R.id.id_task_request_description_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.createTaskRequest(description.getText().toString(), taskId);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return builder.create();
    }

    public void setTaskId(int id) {
        this.taskId = id;
    }

    public void setPresenter(CompletedTasksContract.Presenter presenter) {
        this.presenter = presenter;
    }
}