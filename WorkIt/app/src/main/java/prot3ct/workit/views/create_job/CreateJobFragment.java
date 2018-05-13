package prot3ct.workit.views.create_job;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import prot3ct.workit.R;
import prot3ct.workit.utils.WorkItProgressDialog;
import prot3ct.workit.views.create_job.base.CreateJobContract;
import prot3ct.workit.views.list_jobs.ListJobsActivity;

public class CreateJobFragment extends Fragment implements CreateJobContract.View {
    private CreateJobContract.Presenter presenter;
    private Context context;

    private TextView titleTextView;
    private TextView startDateTextView;
    private EditText lengthEditText;
    private TextView descriptionTextView;
    private TextView cityTextView;
    private TextView addressTextView;
    private TextView rewardTextView;
    private TextView minimalRatingTextView;
    private Button saveTaskButton;

    private Calendar date;

    private WorkItProgressDialog dialog;

    public CreateJobFragment() {
        // Required empty public constructor
    }

    public static CreateJobFragment newInstance() {
        return new CreateJobFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void setPresenter(CreateJobContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_job, container, false);

        this.dialog = new WorkItProgressDialog(context);
        this.titleTextView = (TextView) view.findViewById(R.id.id_title_edit_text);
        this.startDateTextView = (TextView) view.findViewById(R.id.id_choose_start_date_text_view);
        this.lengthEditText = (EditText) view.findViewById(R.id.id_length_edit_text);
        this.descriptionTextView = (TextView) view.findViewById(R.id.id_description_edit_text);
        this.cityTextView = (TextView) view.findViewById(R.id.id_city_edit_text);
        this.addressTextView = (TextView) view.findViewById(R.id.id_address_edit_text);
        this.rewardTextView = (TextView) view.findViewById(R.id.id_reward_edit_text);
        this.minimalRatingTextView = (TextView) view.findViewById(R.id.id_minimal_raiting_to_join_edit_text);
        this.saveTaskButton = (Button) view.findViewById(R.id.id_create_task_btn);


        this.startDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(1);
            }
        });

        this.lengthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(2);
            }
        });

        this.saveTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createTask(
                    titleTextView.getText().toString(),
                    startDateTextView.getText().toString(),
                    lengthEditText.getText().toString(),
                    descriptionTextView.getText().toString(),
                    cityTextView.getText().toString(),
                    addressTextView.getText().toString(),
                    rewardTextView.getText().toString(),
                    minimalRatingTextView.getText().toString()
                );
            }
        });

        return view;
    }

    @Override
    public void showListJobsActivity() {
        Intent intent = new Intent(this.context, ListJobsActivity.class);
        startActivity(intent);
    }

    @Override
    public void notifyError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifySuccessful() {
        Toast.makeText(getContext(), "Task created successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogforLoading() {
        this.dialog.showProgress("Creating task...");
    }

    @Override
    public void dismissDialog() {
        this.dialog.dismissProgress();
    }

    public void showDateTimePicker(final int temp) {
        final Calendar currentDate = Calendar.getInstance();
        this.date = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            boolean first = true;

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (first) {
                    date.set(year, monthOfYear, dayOfMonth);
                    new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            date.set(Calendar.MINUTE, minute);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd,hh:mm");

                            if (temp == 1) {
                                startDateTextView.setText(dateFormat.format(date.getTime()));
                            }
                            else if (temp == 2) {
                                lengthEditText.setText(dateFormat.format(date.getTime()));
                            }
                            first = false;
                        }
                    }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();

                    first = false;
                }
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }
}
