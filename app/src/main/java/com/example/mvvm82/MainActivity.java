package com.example.mvvm82;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvm82.databinding.ActivityMainBinding;
import com.example.mvvm82.databinding.DialogLayoutBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.onItemClick {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding activityMainBinding;
    private RecyclerAdapter adapter = new RecyclerAdapter();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private MainActivityEvents events;
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = activityMainBinding.contentLayout.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        fab = activityMainBinding.addFab;

        adapter.setOnItemClickListener(this);

        events = new MainActivityEvents(this);


        contactViewModel = new ViewModelProvider(this, new ContactViewModelFactory(getApplication())).get(ContactViewModel.class);


        activityMainBinding.setEvents(events);
        activityMainBinding.setViewmodel(contactViewModel);

        contactViewModel.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                adapter.setContacts(contacts);

            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                contactViewModel.deleteContact(adapter.getContact(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onItemClickListener(Contact contact) {
        events.addButtonClickListener(recyclerView, contact, contactViewModel, 1);
    }

    public static class MainActivityEvents {
        private Context context;
        private DialogLayoutBinding dialogLayoutBinding;

        public MainActivityEvents(Context context) {
            this.context = context;

        }

        public void addButtonClickListener(View view, final Contact contact, final ContactViewModel contactViewModel, int insertOrUpdate) {
            dialogLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_layout, null, false);

            dialogLayoutBinding.setContact(contact);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogLayoutBinding.getRoot());
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (insertOrUpdate == 0) {
                        contactViewModel.insertContact(contact);
                    } else {
                        contactViewModel.updateContact(contact);
                    }


                }
            }).setNegativeButton("cancel", null).show();

        }
    }

}