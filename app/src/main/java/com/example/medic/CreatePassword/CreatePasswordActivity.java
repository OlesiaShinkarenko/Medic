package com.example.medic.CreatePassword;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.CreateCard.CreateCardActivity;
import com.example.medic.MainScreen.MainScreenActivity;
import com.example.medic.R;
import com.example.medic.common.RetrofitClient;
import com.example.medic.common.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    TextView skip;
    ImageButton delete;
    ImageView indicator1,indicator2,indicator3, indicator4;
    Integer kol_click = 0;
    String password = "";

    Button number0,number1,number2,number3,number4,number5,number6,number7,number8,number9;

    private static final String MY_SETTINGS = "my_settings_OnCreatePassword";
    private static final String MY_SETTINGS2 = "my_settings_CreateCard";
    private static final String SETTINGS_PASSWORD = "settings_password";
    private static final String MY_SETTINGS_EMAIL = "my_settings_email";
    public static final String PREFERENCES_EMAIL = "Email";
    public static final String PREFERENCES_PASSWORD = "password";
    SharedPreferences sp,sp2,sp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        sp2 = getSharedPreferences(MY_SETTINGS2, Context.MODE_PRIVATE);
        sp3 = getSharedPreferences(SETTINGS_PASSWORD, Context.MODE_PRIVATE);


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
                SharedPreferences.Editor r2 = sp2.edit();
                r2.putBoolean("hasSkipped",true);
                r2.commit();
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
            SharedPreferences.Editor r2 = sp2.edit();
            r2.putBoolean("hasSkipped",false);
            r2.commit();
            SharedPreferences.Editor r3 = sp3.edit();
            r3.putString(PREFERENCES_PASSWORD,password);
            r3.commit();
            sp3 = getSharedPreferences(MY_SETTINGS_EMAIL,Context.MODE_PRIVATE);
            String email = sp3.getString(PREFERENCES_EMAIL, "");
            User user = new User(email, password);
            new Thread(new Runnable() {
                public void run() {
                    try{
                        RetrofitClient.getRetrofitClient().SignUp(user).enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Log.d("true","true");
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.d("false",t.toString());
                            }
                        });
                    }
                    catch (Exception ex){
                        Log.d("not","not");
                    }
            }}).start();
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