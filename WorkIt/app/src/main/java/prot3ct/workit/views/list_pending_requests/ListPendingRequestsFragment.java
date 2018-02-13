package prot3ct.workit.views.list_pending_requests;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import prot3ct.workit.R;
import prot3ct.workit.data.remote.result_models.TaskRequestListViewModel;
import prot3ct.workit.views.list_jobs.ListJobsActivity;
import prot3ct.workit.views.list_pending_requests.base.ListPendingRequestsContract;
import prot3ct.workit.views.task_request_details.TaskRequestDetailsActivity;

public class ListPendingRequestsFragment extends Fragment implements ListPendingRequestsContract.View {
    private ListPendingRequestsContract.Presenter presenter;
    private Context context;

    ArrayAdapter<TaskRequestListViewModel> taskRequestAdapter;
    private ListView taskRequestListView;

    public ListPendingRequestsFragment() {
    }

    public static ListPendingRequestsFragment newInstance() {
        return new ListPendingRequestsFragment();
    }

    @Override
    public void setPresenter(ListPendingRequestsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_pending_requests, container, false);

        this.taskRequestListView = view.findViewById(R.id.id_task_requests_list_view);

        this.presenter.getTaskRequestsForCurrentUser();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void setupTaskRequestsAdapter(final List<? extends TaskRequestListViewModel> taskRequests) {
        this.taskRequestAdapter = new ArrayAdapter<TaskRequestListViewModel>(this.getContext(), -1, (List<TaskRequestListViewModel>) taskRequests) {
            @NonNull
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
                    view = inflater.inflate(R.layout.single_task_request, parent, false);
                }

                TextView taskTitle = view.findViewById(R.id.id_single_task_request_title_text_view);
                TextView taskStatus = view.findViewById(R.id.id_single_task_request_status_text_view);

                taskTitle.setText(taskRequests.get(position).getTaskTitle());
                taskStatus.setText(taskRequests.get(position).getStatus());
                taskTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, TaskRequestDetailsActivity.class);
                        intent.putExtra("taskRequestId", taskRequests.get(position).getTaskRequestId());
                        startActivity(intent);
                    }
                });
                return view;
            }
        };

        this.taskRequestListView.setAdapter(taskRequestAdapter);
    }
}
