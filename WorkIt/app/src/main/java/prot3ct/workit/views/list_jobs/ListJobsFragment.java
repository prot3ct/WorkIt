package prot3ct.workit.views.list_jobs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import prot3ct.workit.R;
import prot3ct.workit.models.base.TaskContract;
import prot3ct.workit.views.create_job.CreateJobActivity;
import prot3ct.workit.views.list_jobs.base.ListJobsContract;
import prot3ct.workit.views.login.LoginActivity;

public class ListJobsFragment extends Fragment implements ListJobsContract.View {
    private ListJobsContract.Presenter presenter;
    private Context context;

    private Button createJobButton;
    private Button logoutButton;

    public ListJobsFragment() {
        // Required empty public constructor
    }

    public static ListJobsFragment newInstance() {
        return new ListJobsFragment();
    }

    @Override
    public void setPresenter(ListJobsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_jobs, container, false);

        this.createJobButton = (Button) view.findViewById(R.id.id_create_job_button);
        this.logoutButton = (Button) view.findViewById(R.id.id_logout_button);

        this.createJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateJobActivity();
            }
        });

        this.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
                notifySuccessful("You have logged out successfully");
                showLoginActivity();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void showCreateJobActivity() {
        Intent intent = new Intent(this.context, CreateJobActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginActivity() {
        Intent intent = new Intent(this.context, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void notifySuccessful(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

//    public void setupNotesAdapter(final List<? extends TaskContract> notes) {
//        this.noteAdapter = new ArrayAdapter<NoteContract>(this.getContext(), -1, (List<NoteContract>) notes) {
//            @NonNull
//            @Override
//            public View getView(final int position, View convertView, ViewGroup parent) {
//                View view = convertView;
//                if (view == null) {
//                    LayoutInflater inflater = LayoutInflater.from(this.getContext());
//                    view = inflater.inflate(R.layout.single_note, parent, false);
//                }
//
//                imagePreview = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.note_image_preview);
//                TextView noteDate = (TextView) view.findViewById(R.id.tv_note_date);
//                TextView noteTitle = (TextView) view.findViewById(R.id.tv_note_title);
//                final ImageButton deleteNoteButton = (ImageButton) view.findViewById(R.id.note_delete_button);
//                noteTextTypeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/Champagne.ttf");
//                noteTitle.setTypeface(noteTextTypeFace);
//
//                String encodedImage = notes.get(position).getPicture();
//                final byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
//                Bitmap bm = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                imagePreview.setImageBitmap(bm);
//
//                view.setOnClickListener(new Button.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showNewNoteActivityWithImage(notes.get(position).get_id(), decodedString, notes.get(position).getTitle());
//                    }
//                });
//
//                deleteNoteButton.setOnClickListener(new Button.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        presenter.deleteNoteById(notes.get(position).get_id());
//                        noteAdapter.remove(noteAdapter.getItem(position));
//                        noteAdapter.notifyDataSetChanged();
//                    }
//                });
//
//                noteDate.setText(notes.get(position).getDate());
//                noteTitle.setText(notes.get(position).getTitle());
//
//                return view;
//            }
//        };
//
//        this.listViewNotes.setAdapter(noteAdapter);
//    }
}
