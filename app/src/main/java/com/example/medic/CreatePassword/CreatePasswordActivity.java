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
import com.example.medic.common.DBHandlerMedic;
import com.example.medic.common.RefreshAccess;
import com.example.medic.common.RetrofitClient;
import com.example.medic.common.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    TextView skip,create_password_text;
    ImageButton delete;
    ImageView indicator1,indicator2,indicator3, indicator4;
    Integer kol_click = 0;

    String password = "";
    boolean  hasSkipped;
    Intent i;
    DBHandlerMedic dbHandlerMedic;

    Button number0,number1,number2,number3,number4,number5,number6,number7,number8,number9;

    private static final String MY_SETTINGS = "my_settings_OnCreatePassword";
    private static final String MY_SETTINGS_EMAIL = "my_settings_email";
    private static final String PREFERENCES_EMAIL = "Email";
    private static final String SETTING_ACCESS= "setting_refresh";
    private static final String ACCESS = "refresh";
    RefreshAccess refresh = new RefreshAccess();
    SharedPreferences sp,sp2,sp3;

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
        create_password_text = findViewById(R.id.create_password_text);
        delete.setEnabled(false);
        hasSkipped = sp.getBoolean("hasSkipped",true);
        if (!hasSkipped){
            skip.setVisibility(View.INVISIBLE);
            create_password_text.setText("Введите пароль");
        }else {
            skip.setVisibility(View.VISIBLE);
            create_password_text.setText(R.string.create_password);
        }

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

        dbHandlerMedic = new DBHandlerMedic(CreatePasswordActivity.this);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Сохраняем, что пропущено
                SharedPreferences.Editor r = sp.edit();
                r.putBoolean("hasSkipped",true);
                r.commit();
                i = new Intent(CreatePasswordActivity.this, MainScreenActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    private void CheckClick(){
        if (kol_click ==0){
            delete.setEnabled(false);
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
                sp2 = getSharedPreferences(MY_SETTINGS_EMAIL, Context.MODE_PRIVATE);
                String email = sp2.getString(PREFERENCES_EMAIL, "");
                User user = new User(email, password);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        hasSkipped = sp.getBoolean("hasSkipped",true);
                        if (!hasSkipped){
                            RetrofitClient.getRetrofitClient().SignIn(user).enqueue(new Callback<RefreshAccess>() {
                                @Override
                                public void onResponse(Call<RefreshAccess> call, Response<RefreshAccess> response) {
                                    refresh = response.body();
                                    sp3 = getSharedPreferences(SETTING_ACCESS,Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sp3.edit();
                                    editor.putString(ACCESS, refresh.getAccess());
                                    Log.d("acess",refresh.getAccess());
                                    editor.commit();
                                    sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor r = sp.edit();
                                    r.putBoolean("hasSkipped",false);
                                    r.commit();
                                    i = new Intent(CreatePasswordActivity.this,MainScreenActivity.class);
                                    startActivity(i);
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<RefreshAccess> call, Throwable t) {

                                }
                            });
                        }else {
                           RetrofitClient.getRetrofitClient().SignUp(user).enqueue(new Callback<RefreshAccess>() {
                               @Override
                               public void onResponse(Call<RefreshAccess> call, Response<RefreshAccess> response) {
                                   refresh = response.body();
                                   sp3 = getSharedPreferences(SETTING_ACCESS,Context.MODE_PRIVATE);
                                   SharedPreferences.Editor editor = sp3.edit();
                                   editor.putString(ACCESS, refresh.getAccess());
                                   editor.commit();
                                   sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                                   SharedPreferences.Editor r = sp.edit();
                                   r.putBoolean("hasSkipped",false);
                                   r.commit();
                                   i = new Intent(CreatePasswordActivity.this, CreateCardActivity.class);
                                   startActivity(i);
                                   finish();
                               }

                               @Override
                               public void onFailure(Call<RefreshAccess> call, Throwable t) {

                               }
                           });
                        }
                    }
                }).start();
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