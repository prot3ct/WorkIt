package prot3ct.workit.views.create_job;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import prot3ct.workit.R;
import prot3ct.workit.views.create_job.base.CreateJobContract;

public class CreateJobFragment extends Fragment implements CreateJobContract.View {
    private CreateJobContract.Presenter presenter;
    private Context context;

    private TextView startDateTextView;
    private TextView endDateTextView;

    private Calendar date;

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

        this.startDateTextView = (TextView) view.findViewById(R.id.id_choose_start_date_text_view);
        this.endDateTextView = (TextView) view.findViewById(R.id.id_choose_end_date_text_view);

        this.startDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(1);
            }
        });

        this.endDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(2);
            }
        });

        return view;
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
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

                            if (temp == 1) {
                                startDateTextView.setText(dateFormat.format(date.getTime()));
                            }
                            else if (temp == 2) {
                                endDateTextView.setText(dateFormat.format(date.getTime()));
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
