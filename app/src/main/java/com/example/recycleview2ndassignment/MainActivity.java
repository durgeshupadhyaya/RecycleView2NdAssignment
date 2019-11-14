package com.example.recycleview2ndassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recycleview2ndassignment.Model.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AutoCompleteTextView actImage;
    private Spinner spinnerCountry;
    private EditText etName, etDob, etPhone, etEmail;
    private RadioGroup rgGender;
    private Button btnSubmit, btnView;

    private List<Person> peopleList = new ArrayList<>();
    String[] people ={"Durga", "Bhuwan", "Roshan", "Tara", "deepak", "Sanjay", "Gopal"};
    String name, dob, country, gender, phone, email, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDob = findViewById(R.id.etDoB);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        actImage = findViewById(R.id.actImage);
        spinnerCountry = findViewById(R.id.spinnerCountry);
        rgGender = findViewById(R.id.rgGender);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnView = findViewById(R.id.btnView);

        onRadioButtonClicked();
        onSpinnerClicked();
        onAutoCompleteTextViewClicked();
        etDob.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    private void onRadioButtonClicked(){
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbMale:
                        gender = "Male";
                        break;
                    case R.id.rbFemale:
                        gender = "Female";
                        break;
                    case R.id.rbOther:
                        gender = "other";
                        break;
                }
            }
        });
    }

    private void onDobClicked(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = month + "/" + dayOfMonth + "/" + year;
                etDob.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void onSpinnerClicked(){
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "Please select a country.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onAutoCompleteTextViewClicked(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.textview_values, people);
        actImage.setAdapter(arrayAdapter);
        actImage.setThreshold(1);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.etDoB){
            onDobClicked();
        }

        if(v.getId() == R.id.btnSubmit){
            name = etName.getText().toString().trim();
            dob = etDob.getText().toString();
            phone = etPhone.getText().toString().trim();
            email = etEmail.getText().toString().trim();
            image = actImage.getText().toString().trim();

            String uri = "@drawable/"+image;
            int resID = getResources().getIdentifier(uri, null, getPackageName());

            if(validation()){
                peopleList.add(new Person(name,dob, gender, country, phone, email, resID));
                Toast.makeText(this, "New Contact Data Added", Toast.LENGTH_SHORT).show();
            }
        }

        if(v.getId() == R.id.btnView){

            if(peopleList.size()==0){
                Toast.makeText(this, "You have not added anyone into your contact list.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, ContactsListActivity.class);
                intent.putExtra("PERSON_DATA", (Serializable) peopleList);
                startActivity(intent);
            }
        }
    }

    private boolean validation(){
        if(TextUtils.isEmpty(name)){
            etName.setError("Name must not be empty!");
            etName.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(dob)){
            etDob.setError("");
            Toast.makeText(this, "Date of birth must not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(gender)){
            Toast.makeText(this, "Please select your gender!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(country)){
            Toast.makeText(this, "Please select a country!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(country.equals("Select Your Country")){
            Toast.makeText(this, "Please select a country!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(phone)){
            etPhone.setError("Phone must not be empty!");
            etPhone.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(email)){
            etEmail.setError("Email Address must not be empty!");
            etEmail.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(image)){
            actImage.setError("Image Name must not be empty!");
            actImage.requestFocus();
            return false;
        }
        return true;
    }
}

