package prot3ct.workit.views.completed_tasks;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import prot3ct.workit.R;
import prot3ct.workit.models.base.TaskContract;

public class CompletedTaskAdapter extends RecyclerView.Adapter<CompletedTaskAdapter.TaskViewHolder> {
    List<? extends TaskContract> tasks;
    private Context context;

    CompletedTaskAdapter(List<? extends TaskContract> tasks, Context context){
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public CompletedTaskAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_completed_task, parent, false);
        CompletedTaskAdapter.TaskViewHolder pvh = new CompletedTaskAdapter.TaskViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CompletedTaskAdapter.TaskViewHolder holder, final int position) {
        holder.taskTitle.setText(tasks.get(position).getTitle());
        holder.raitingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView taskTitle;
        TextView status;
        ImageView raitingButton;

        TaskViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            taskTitle = itemView.findViewById(R.id.id_task_title);
            status = itemView.findViewById(R.id.id_task_status);
            raitingButton = itemView.findViewById(R.id.id_task_give_raiting);
        }
    }
}
