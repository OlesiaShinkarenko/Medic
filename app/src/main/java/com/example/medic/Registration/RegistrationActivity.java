package com.example.medic.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.medic.R;
import com.example.medic.VerificationActivity;

public class RegistrationActivity extends AppCompatActivity {

    private Button buttonNext;
    private EditText editText_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buttonNext = findViewById(R.id.buttonNext);
        editText_email = findViewById(R.id.editText_email);
        editText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonNext.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, VerificationActivity.class);
                startActivity(i);
            }
        });
    }
}