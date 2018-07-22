package com.walk.angel.angelwalk.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.walk.angel.angelwalk.R;
import com.walk.angel.angelwalk.recycler.Contact;
import com.walk.angel.angelwalk.recycler.ContactsAdapter;

public class SightsActivity extends AppCompatActivity {
    ContactsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);
        setupViews();
    }

    void setupViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Setup RecyclerView, associated adapter, and layout manager.
        adapter = new ContactsAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Populate contact list.
        adapter.addMoreContacts(Contact.createContactsList(20));

        // Setup button to append additional contacts.
        Button addMoreButton = (Button) findViewById(R.id.add_more_contacts);
        addMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addMoreContacts(Contact.createContactsList(5));
            }
        });
    }
}
