package prot3ct.workit.views.job_details;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import prot3ct.workit.R;
import prot3ct.workit.models.base.TaskContract;
import prot3ct.workit.utils.WorkItProgressDialog;
import prot3ct.workit.views.job_details.base.JobDetailsContract;
import prot3ct.workit.views.list_task_requests.ListTaskRequestsActivity;

public class JobDetailsFragment extends Fragment implements JobDetailsContract.View, OnMapReadyCallback {
    private JobDetailsContract.Presenter presenter;
    private Context context;
    private TaskContract taskDetails;

    private GoogleMap mMap;

    private TaskRequestDialog applyForTaskDialog;
    private TextView taskTitle;
    private TextView taskDescription;
    private TextView taskStartDate;
    private TextView reward;
    private TextView city;
    private WorkItProgressDialog dialog;

    public JobDetailsFragment() {
        // Required empty public constructor
    }

    public static JobDetailsFragment newInstance() {
        return new JobDetailsFragment();
    }

    @Override
    public void setPresenter(JobDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_details, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        applyForTaskDialog = new TaskRequestDialog();
        applyForTaskDialog.setPresenter(this.presenter);
        this.dialog = new WorkItProgressDialog(context);
        this.taskTitle = view.findViewById(R.id.id_title_details_edit_text);
        this.reward = view.findViewById(R.id.id_reward_details_edit_text);
        this.taskDescription = view.findViewById(R.id.id_description_details_edit_text);
        this.taskStartDate = view.findViewById(R.id.id_date_details_text_view);
        this.city = view.findViewById(R.id.id_city_details_text_view);

        this.taskDetails = (TaskContract) this.getActivity().getIntent().getSerializableExtra("TaskDetails");
        applyForTaskDialog.setTaskId(taskDetails.getId());

        this.taskTitle.setText(taskDetails.getTitle());
        this.taskDescription.setText(taskDetails.getDescription());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(taskDetails.getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date currentDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        this.taskStartDate.setText(getOrdinal(calendar.get(Calendar.DAY_OF_MONTH)) + " " + getMonthForInt(calendar.get(Calendar.MONTH)) + " at " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        this.reward.setText("BGN " + taskDetails.getReward() +"/hr");
        this.city.setText(taskDetails.getCity() + ", " + taskDetails.getAddress());// for


//        this.applyForTaskButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                applyForTaskDialog.show(getFragmentManager(), "text_popup");
//            }
//        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void notifySuccessful(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDialogforLoading() {
        this.dialog.showProgress("Logging in...");
    }

    @Override
    public void dismissDialog() {
        this.dialog.dismissProgress();
    }

    private String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

    private String getOrdinal(int i) {
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        presenter.getLatLng(taskDetails.getCity() + ", " + taskDetails.getAddress());
    }

    @Override
    public void updateMap(double lat, double lng) {
        LatLng location = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(location));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17.0f));
    }
}
