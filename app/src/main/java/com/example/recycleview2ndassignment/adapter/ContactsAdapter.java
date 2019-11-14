package com.example.recycleview2ndassignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview2ndassignment.Model.Person;
import com.example.recycleview2ndassignment.R;
import com.example.recycleview2ndassignment.ShowContactDetailsActivity;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactHolder>{

    List<Person> personList;
    private Context context;
    Person person;

    public ContactsAdapter(List<Person> personList, Context context) {
        this.personList = personList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contacts_list, parent, false);
        ContactHolder contactHolder = new ContactHolder(itemView);
        return contactHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, final int position) {
        person = personList.get(position);
        holder.txtPerson.setText(person.getName());
        holder.imgPerson.setImageResource(person.getImage());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ShowContactDetailsActivity.class);
                person = personList.get(position);
                i.putExtra("CONTACT_NAME", person.getName());
                i.putExtra("CONTACT_DOB", person.getDob());
                i.putExtra("CONTACT_GENDER", person.getGender());
                i.putExtra("CONTACT_COUNTRY", person.getCountry());
                i.putExtra("CONTACT_PHONE", person.getPhone());
                i.putExtra("CONTACT_EMAIL", person.getEmail());
                i.putExtra("CONTACT_IMAGE", String.valueOf(person.getImage()));
                System.out.println(person.getImage());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }


    public class ContactHolder extends RecyclerView.ViewHolder{
        ImageView imgPerson;
        TextView txtPerson;
        RelativeLayout relativeLayout;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            imgPerson = itemView.findViewById(R.id.imgPerson);
            txtPerson = itemView.findViewById(R.id.txtPerson);
            relativeLayout = itemView.findViewById(R.id.rlContact);
        }
    }
}
