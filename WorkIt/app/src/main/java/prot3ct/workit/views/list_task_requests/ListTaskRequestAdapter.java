package prot3ct.workit.views.list_task_requests;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import prot3ct.workit.R;
import prot3ct.workit.view_models.TaskRequestListViewModel;
import prot3ct.workit.views.list_task_requests.base.ListTaskRequestContract;

public class ListTaskRequestAdapter extends RecyclerView.Adapter<ListTaskRequestAdapter.TaskViewHolder> {
    private ListTaskRequestContract.Presenter presenter;
    private List<TaskRequestListViewModel> requests;
    private Context context;

    ListTaskRequestAdapter(List<TaskRequestListViewModel> requests, Context context, ListTaskRequestContract.Presenter presenter){
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
        holder.fullName.setText(requests.get(position).getFullName());

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.acceptTaskRequest(requests.get(position).getTaskRequestId(), 3);

            }
        });

        holder.declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.declineTaskRequest(requests.get(position).getTaskRequestId(), 2);
                requests.remove(position);
                notifyDataSetChanged();
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
