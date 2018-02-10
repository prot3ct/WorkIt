package prot3ct.workit.views.job_details;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import prot3ct.workit.R;

public class TaskRequestDialog extends Dialog {
    private JobDetailsPresenter presenter;

    private Activity activity;
    private Dialog dialog;
    private Button confirmButton;
    private Button cancelButton;

    public TaskRequestDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_task_request);
        this.confirmButton = (Button) findViewById(R.id.id_task_request_confirm_button);
        this.cancelButton = (Button) findViewById(R.id.id_task_request_cancel_button);
        this.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRequest();
            }
        });
        this.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelRequest();
            }
        });
    }

    private void createRequest() {
        // TO DO this.presenter
        activity.finish();
    }

    private void cancelRequest() {
        dismiss();
    }
}