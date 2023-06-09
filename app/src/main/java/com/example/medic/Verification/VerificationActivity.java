package com.example.medic.Verification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.CreatePassword.CreatePasswordActivity;
import com.example.medic.R;

import java.security.SecureRandom;

public class VerificationActivity extends AppCompatActivity {

    ImageButton btn_back;
    EditText number1,number2, number3,number4;
    TextView return_code;
    private static final String MY_SETTINGS_EMAIL = "my_settings_email";

    private static final String PREFERENCES_EMAIL = "Email";
    SharedPreferences sp;

    String code_from_text = "", email, randomCode;
    CountDownTimer count;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        btn_back = findViewById(R.id.btn_back);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        number3 = findViewById(R.id.number3);
        number4 = findViewById(R.id.number4);
        return_code = findViewById(R.id.return_code);

        sp = getSharedPreferences(MY_SETTINGS_EMAIL, Context.MODE_PRIVATE);

        email = sp.getString(PREFERENCES_EMAIL,"");
        New_code();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        number1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==1){
                    number2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        number2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() ==1){
                    number3.requestFocus();
                    code_from_text+=charSequence;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        number3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() ==1){
                    number4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        number4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               code_from_text =  number1.getText().toString()+number2.getText()+number3.getText()+charSequence;
                if( code_from_text.equals(randomCode)){
                    Intent intent = new Intent(VerificationActivity.this, CreatePasswordActivity.class);
                    startActivity(intent);
                    count.cancel();
                    finish();
                }else{
                    Toast.makeText(VerificationActivity.this, "Неверный", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void New_code(){
        SecureRandom random = new SecureRandom();
        int min =1000;
        int max = 9999;
        int diff = max-min;
        int rn_number = random.nextInt(diff+1);
        rn_number+=min;
        randomCode = String.valueOf(rn_number);
        Log.e("d",randomCode);
        EmailSend emailSend = new EmailSend();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    emailSend.setBody(randomCode);
                    emailSend.setSubject("Code");
                    emailSend.setTo(email);
                    emailSend.send();
                } catch (Exception e) {
                }
            }
        }).start();
        count = new CountDownTimer(60000, 1000){

            @Override
            public void onTick(long l) {
                String javaFormatString  =  getString(R.string.code_again);
                String time = String.valueOf(l/1000);
                String  substitutedString  =  String.format(javaFormatString,time);
                return_code.setText(substitutedString);
            }

            @Override
            public void onFinish() {
                New_code();
            }
        }.start();
    }

}