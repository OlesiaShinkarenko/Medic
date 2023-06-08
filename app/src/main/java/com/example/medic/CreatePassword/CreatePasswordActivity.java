package com.example.medic.CreatePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.medic.CreateCard.CreateCardActivity;
import com.example.medic.R;

public class CreatePasswordActivity extends AppCompatActivity {

    TextView skip;
    private static final String MY_SETTINGS = "my_settings_OnCreatePassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        skip = findViewById(R.id.skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                SharedPreferences.Editor r = sp.edit();
                r.putBoolean("hasSkipped",true);
                r.commit();
                Intent i = new Intent(CreatePasswordActivity.this, CreateCardActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}