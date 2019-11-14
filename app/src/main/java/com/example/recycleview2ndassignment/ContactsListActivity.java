package com.example.recycleview2ndassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.recycleview2ndassignment.Model.Person;
import com.example.recycleview2ndassignment.adapter.ContactsAdapter;

import java.util.List;

public class ContactsListActivity extends AppCompatActivity {

    List<Person> person_data;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        recyclerView = findViewById(R.id.rvContacts);

        Intent intent = getIntent();
        person_data = (List<Person>) intent.getSerializableExtra("PERSON_DATA");

        ContactsAdapter contactsAdapter = new ContactsAdapter(person_data, ContactsListActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactsAdapter);
    }
}