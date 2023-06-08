package com.example.medic.CreatePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medic.CreateCard.CreateCardActivity;
import com.example.medic.MainScreenActivity;
import com.example.medic.R;

public class CreatePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    TextView skip;
    ImageButton delete;
    ImageView indicator1,indicator2,indicator3, indicator4;
    Integer kol_click = 0;
    String password = "";

    Button number0,number1,number2,number3,number4,number5,number6,number7,number8,number9;

    private static final String MY_SETTINGS = "my_settings_OnCreatePassword";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);

        skip = findViewById(R.id.skip);
        indicator1 = findViewById(R.id.indicator1);
        indicator2 = findViewById(R.id.indicator2);
        indicator3 = findViewById(R.id.indicator3);
        indicator4 = findViewById(R.id.indicator4);
        number0 = findViewById(R.id.number0);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        number3 = findViewById(R.id.number3);
        number4 = findViewById(R.id.number4);
        number5 = findViewById(R.id.number5);
        number6 = findViewById(R.id.number6);
        number7 = findViewById(R.id.number7);
        number8 = findViewById(R.id.number8);
        number9 = findViewById(R.id.number9);
        delete = findViewById(R.id.delete);
        delete.setEnabled(false);

        number0.setOnClickListener(this::onClick);
        number1.setOnClickListener(this::onClick);
        number2.setOnClickListener(this::onClick);
        number3.setOnClickListener(this::onClick);
        number4.setOnClickListener(this::onClick);
        number5.setOnClickListener(this::onClick);
        number6.setOnClickListener(this::onClick);
        number7.setOnClickListener(this::onClick);
        number8.setOnClickListener(this::onClick);
        number9.setOnClickListener(this::onClick);
        delete.setOnClickListener(this::onClick);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor r = sp.edit();
                r.putBoolean("hasSkipped",true);
                r.commit();
                Intent i = new Intent(CreatePasswordActivity.this, MainScreenActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    private void CheckClick(){
        if (kol_click ==0){
            indicator1.setImageResource(R.drawable.create_password_false);
            indicator2.setImageResource(R.drawable.create_password_false);
            indicator3.setImageResource(R.drawable.create_password_false);
            indicator4.setImageResource(R.drawable.create_password_false);
        }else if (kol_click ==1){
            delete.setEnabled(true);
            indicator1.setImageResource(R.drawable.create_password_true);
            indicator2.setImageResource(R.drawable.create_password_false);
            indicator3.setImageResource(R.drawable.create_password_false);
            indicator4.setImageResource(R.drawable.create_password_false);
        }else if(kol_click==2){
            indicator1.setImageResource(R.drawable.create_password_true);
            indicator2.setImageResource(R.drawable.create_password_true);
            indicator3.setImageResource(R.drawable.create_password_false);
            indicator4.setImageResource(R.drawable.create_password_false);
        }else if(kol_click==3){
            indicator2.setImageResource(R.drawable.create_password_true);
            indicator1.setImageResource(R.drawable.create_password_true);
            indicator3.setImageResource(R.drawable.create_password_true);
            indicator4.setImageResource(R.drawable.create_password_false);
        }else if (kol_click ==4) {
            indicator4.setImageResource(R.drawable.create_password_true);
            SharedPreferences.Editor r = sp.edit();
            r.putBoolean("hasSkipped",false);
            r.commit();
            Intent i = new Intent(CreatePasswordActivity.this, CreateCardActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.delete){
           password = password.substring(0,password.length()-1);
           kol_click --;
        }else {
            Button button = (Button) view;
            password += button.getText().toString();
            kol_click++;
        }
        CheckClick();
    }
}