package prot3ct.workit.views.list_jobs;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import prot3ct.workit.R;
import prot3ct.workit.models.base.TaskContract;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TaskViewHolder> {
    List<? extends TaskContract> tasks;

    RVAdapter(List<? extends TaskContract> tasks){
        this.tasks = tasks;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task, parent, false);
        TaskViewHolder pvh = new TaskViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.timeLeft.setText("1 day");
        holder.taskTitle.setText(tasks.get(position).getTitle());
        holder.taskCreator.setText(tasks.get(position).getCreatorEmail());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView timeLeft;
        TextView taskTitle;
        TextView taskCreator;

        TaskViewHolder(View itemView) {
            super(itemView);
            timeLeft = itemView.findViewById(R.id.id_task_time_left);
            taskTitle = itemView.findViewById(R.id.id_task_title);
            taskCreator = itemView.findViewById(R.id.id_task_creator);
        }
    }
}
