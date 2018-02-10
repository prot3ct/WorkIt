package prot3ct.workit.views.job_details;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import prot3ct.workit.R;
import prot3ct.workit.views.job_details.base.JobDetailsContract;

public class TaskRequestDialog extends DialogFragment {
    private JobDetailsContract.Presenter presenter;
    private int taskId;
    private View view;
    private Button confirmButton;
    private Button cancelButton;
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

    public void setPresenter(JobDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}