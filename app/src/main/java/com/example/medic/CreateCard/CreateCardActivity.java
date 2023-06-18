package com.example.medic.CreateCard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.MainScreen.MainScreenActivity;
import com.example.medic.R;
import com.example.medic.common.CardPatient;
import com.example.medic.common.DBHandlerMedic;
import com.example.medic.common.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCardActivity extends AppCompatActivity implements TextWatcher {

    Spinner spinner_gender;
    String [] list;

    TextView skip;
    DBHandlerMedic dbHandlerMedic;

    Button btn_create_card;
    EditText editText_name, editText_lastname, editText_surname, editText_date_birthday;

    private static final String MY_SETTINGS = "my_settings_CreateCard";
    private static final String SETTINGS_ACCESS= "access";
    private static final String ACCESS = "access";
    SharedPreferences sp,sp2;


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

        sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        dbHandlerMedic = new DBHandlerMedic(this);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor r = sp.edit();
                r.putBoolean("hasSkipped",true);
                r.commit();
                NextActivity();
            }
        });
        sp2 = getSharedPreferences(SETTINGS_ACCESS,Context.MODE_PRIVATE);
        Log.d("fd", sp2.getString(ACCESS,""));
        btn_create_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor r = sp.edit();
                r.putBoolean("hasSkipped",false);
                r.commit();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            CardPatient cardPatient = new CardPatient(editText_name.getText().toString(),editText_lastname.getText().toString(),
                                    editText_surname.getText().toString(),editText_date_birthday.getText().toString(), spinner_gender.getSelectedItem().toString());
                            RetrofitClient.getRetrofitClient().CreateCardPatient(cardPatient, "Bearer "+sp2.getString(ACCESS,"")).enqueue(new Callback<CardPatient>() {
                                @Override
                                public void onResponse(Call<CardPatient> call, Response<CardPatient> response) {
                                    Log.d("res",String.valueOf(response.code()));
                                    if(response.code()==201){
                                        Log.d("CREATE",response.body().getId().toString());
                                        dbHandlerMedic.addCardPatient(response.body());
                                        NextActivity();
                                    }
                                }
                                @Override
                                public void onFailure(Call<CardPatient> call, Throwable t) {
                                    Log.d("false","false");
                                }
                            });

                        }catch (Exception e){
                            Log.d("f",e.getMessage());
                        }
                    }
                }).start();
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