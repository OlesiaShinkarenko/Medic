package com.example.medic.CreateCard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.medic.MainScreen.MainScreenActivity;
import com.example.medic.R;

public class CreateCardActivity extends AppCompatActivity implements TextWatcher {

    Spinner spinner_gender;
    String [] list;

    TextView skip;

    Button btn_create_card;
    EditText editText_name, editText_lastname, editText_surname, editText_date_birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        spinner_gender = findViewById(R.id.spinner_gender);
        list  = getResources().getStringArray(R.array.list_gender);
        btn_create_card = findViewById(R.id.btn_create_card);
        editText_name = findViewById(R.id.editText_name);
        editText_lastname = findViewById(R.id.editText_lastname);
        editText_surname = findViewById(R.id.editText_surname);
        editText_date_birthday = findViewById(R.id.editText_date_birthday);
        skip = findViewById(R.id.skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextActivity();
            }
        });

        btn_create_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextActivity();
            }
        });

        editText_name.addTextChangedListener(this);
        editText_lastname.addTextChangedListener(this);
        editText_surname.addTextChangedListener(this);
        editText_date_birthday.addTextChangedListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.spinner_item, list);
        spinner_gender.setAdapter(adapter);



    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(editText_name.getText().length() == 0 ||editText_lastname.getText().length() == 0||editText_surname.getText().length() == 0||editText_date_birthday.getText().length() == 0) {
            btn_create_card.setEnabled(false);
        }else
        {
            btn_create_card.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private void NextActivity(){
        Intent i = new Intent(CreateCardActivity.this, MainScreenActivity.class);
        startActivity(i);
        finish();
    }
}