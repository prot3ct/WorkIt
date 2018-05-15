package prot3ct.workit.views.list_task_requests;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.nio.file.attribute.PosixFileAttributes;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import prot3ct.workit.R;
import prot3ct.workit.data.remote.result_models.TaskRequestListViewModel;
import prot3ct.workit.models.base.TaskContract;
import prot3ct.workit.views.job_details.JobDetailsActivity;
import prot3ct.workit.views.list_task_requests.base.ListTaskRequestContract;

public class SingleTaskRequestAdapter extends RecyclerView.Adapter<SingleTaskRequestAdapter.TaskViewHolder> {
    private ListTaskRequestContract.Presenter presenter;
    private List<TaskRequestListViewModel> requests;
    private Context context;

    SingleTaskRequestAdapter(List<TaskRequestListViewModel> requests, Context context, ListTaskRequestContract.Presenter presenter){
        this.requests = requests;
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task_request, parent, false);
        TaskViewHolder pvh = new TaskViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, final int position) {
        holder.fullName.setText(requests.get(position).getName());

        Log.d("A123Sd", requests.get(position).getTaskRequestId() +"");
        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateTaskRequest(requests.get(position).getTaskRequestId(), 27);
            }
        });

        holder.declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateTaskRequest(requests.get(position).getTaskRequestId(), 26);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView fullName;
        Button acceptButton;
        Button declineButton;

        TaskViewHolder(View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.id_full_name_text_view);
            acceptButton = itemView.findViewById(R.id.id_accept_task_request_button);
            declineButton = itemView.findViewById(R.id.id_decline_task_request_button);
        }
    }
}
