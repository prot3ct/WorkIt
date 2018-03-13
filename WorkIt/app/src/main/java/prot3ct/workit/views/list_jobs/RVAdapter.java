package prot3ct.workit.views.list_jobs;


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
import prot3ct.workit.views.job_details.JobDetailsActivity;
import prot3ct.workit.views.login.LoginActivity;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TaskViewHolder> {
    List<? extends TaskContract> tasks;
    private Context context;

    RVAdapter(List<? extends TaskContract> tasks, Context context){
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task, parent, false);
        TaskViewHolder pvh = new TaskViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, final int position) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(tasks.get(position).getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = Calendar.getInstance().getTime();
        Log.d("CECCO", Integer.toString(currentDate.getDay()));
        Log.d("CECCO", format.format(date));
        if (currentDate.getDay() == date.getDay()) {
            holder.startTime.setText("Today");
            if (date.getHours() != currentDate.getHours()) {
                holder.timeLeft.setText(date.getHours() - currentDate.getHours() + " hours left to respond");
            }
            else {
                holder.timeLeft.setText(date.getMinutes() - currentDate.getMinutes() + " minutes left to respond");
            }
        }
        else if (currentDate.getDay() - 1 == date.getDay()) {
            holder.startTime.setText("Tomorrow");
        }
        else {
            holder.startTime.setText(date.getDay() + "." + date.getMonth());
        }
        holder.taskTitle.setText(tasks.get(position).getTitle());
        holder.taskCreator.setText("for " + tasks.get(position).getCreatorEmail());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JobDetailsActivity.class);
                intent.putExtra("TaskDetails", tasks.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView startTime;
        TextView timeLeft;
        TextView taskTitle;
        TextView taskCreator;

        TaskViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            startTime = itemView.findViewById(R.id.id_task_start_time);
            timeLeft = itemView.findViewById(R.id.id_task_time_left);
            taskTitle = itemView.findViewById(R.id.id_task_title);
            taskCreator = itemView.findViewById(R.id.id_task_creator);
        }
    }
}
