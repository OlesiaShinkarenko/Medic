package com.example.medic.Registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.MainScreen.MainScreenActivity;
import com.example.medic.R;
import com.example.medic.Verification.VerificationActivity;

public class RegistrationActivity extends AppCompatActivity {

    private Button buttonNext;
    private EditText editText_email;

    private String emailPattern = "[a-z0-9._-]+@[a-z0-9._-]+\\.+[a-z]+";
    private String email;
    private SharedPreferences sp, sp2;

    private static final String MY_SETTINGS = "my_settings_OnCreatePassword";
    private static final String MY_SETTINGS_EMAIL = "my_settings_email";

    public static final String PREFERENCES_EMAIL = "Email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        sp= getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        boolean hasSkipped = sp.getBoolean("hasSkipped",true);
        if(!hasSkipped){
            Intent i = new Intent(RegistrationActivity.this, MainScreenActivity.class);
            startActivity(i);
            finish();
        }

        buttonNext = findViewById(R.id.buttonNext);
        editText_email = findViewById(R.id.editText_email);
        editText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    buttonNext.setEnabled(true);
                }else{
                    buttonNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editText_email.getText().toString();
                if(email.matches(emailPattern)){
                    Intent i = new Intent(RegistrationActivity.this, VerificationActivity.class);
                    sp2 = getSharedPreferences(MY_SETTINGS_EMAIL,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp2.edit();
                    editor.putString(PREFERENCES_EMAIL, email);
                    editor.commit();
                    startActivity(i);
                }else{
                    Toast.makeText(RegistrationActivity.this, "Email-адрес некорректный!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}