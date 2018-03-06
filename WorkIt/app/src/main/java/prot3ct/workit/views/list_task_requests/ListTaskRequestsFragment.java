package prot3ct.workit.views.list_task_requests;

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
import prot3ct.workit.views.task_request_details.TaskRequestDetailsActivity;
import prot3ct.workit.views.list_task_requests.base.ListTaskRequestContract;


public class ListTaskRequestsFragment extends Fragment implements ListTaskRequestContract.View {
    private ListTaskRequestContract.Presenter presenter;
    private Context context;

    private int taskId;

    ArrayAdapter<TaskRequestListViewModel> taskRequestAdapter;
    private ListView taskRequestListView;

    public ListTaskRequestsFragment() {
        // Required empty public constructor
    }

    public static ListTaskRequestsFragment newInstance() {
        return new ListTaskRequestsFragment();
    }

    @Override
    public void setPresenter(ListTaskRequestContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_task_requests, container, false);
        this.taskId = getActivity().getIntent().getIntExtra("TaskId", 0);
        this.taskRequestListView = view.findViewById(R.id.id_task_requests_list_view);

        presenter.getTaskRequestsForTask(taskId);

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

                TextView name = view.findViewById(R.id.id_single_user_request_username_text_view);

                name.setText(taskRequests.get(position).getName());
                name.setOnClickListener(new View.OnClickListener() {
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
