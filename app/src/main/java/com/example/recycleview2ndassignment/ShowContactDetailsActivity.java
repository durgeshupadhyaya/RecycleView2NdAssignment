package com.example.recycleview2ndassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowContactDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtName, txtDob, txtGender, txtCountry, txtPhone, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact_details);

        Intent intent = getIntent();

        String name = intent.getStringExtra("CONTACT_NAME");
        String dob = intent.getStringExtra("CONTACT_DOB");
        String gender = intent.getStringExtra("CONTACT_GENDER");
        String country = intent.getStringExtra("CONTACT_COUNTRY");
        String phone = intent.getStringExtra("CONTACT_PHONE");
        String email = intent.getStringExtra("CONTACT_EMAIL");
        String image = intent.getStringExtra("CONTACT_IMAGE");
        int img = Integer.parseInt(image);

        imageView = findViewById(R.id.imageView);
        txtName = findViewById(R.id.txtName);
        txtDob = findViewById(R.id.txtDob);
        txtGender = findViewById(R.id.txtGender);
        txtCountry = findViewById(R.id.txtCountry);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);

        imageView.setImageResource(img);
        txtName.setText(name);
        txtDob.setText(dob);
        txtGender.setText(gender);
        txtCountry.setText(country);
        txtEmail.setText(email);
        txtPhone.setText(phone);
    }
}
