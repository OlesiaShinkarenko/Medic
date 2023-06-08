package com.example.medic.CreateCard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.medic.R;

public class CreateCardActivity extends AppCompatActivity {

    Spinner spinner_gender;
    String [] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        spinner_gender = findViewById(R.id.spinner_gender);
        list  = getResources().getStringArray(R.array.list_gender);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        spinner_gender.setAdapter(adapter);
    }
}