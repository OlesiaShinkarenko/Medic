package com.example.medic.PayOrder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.MainScreen.MainScreenActivity;
import com.example.medic.R;
import com.example.medic.common.DBHandlerMedic;

public class PayOrderActivity extends AppCompatActivity {
    Handler handler = new Handler();
    ProgressBar progress_bar;
    TextView text_after_progress;
    LinearLayout layout,layout2;
    Button button_main;
    private DBHandlerMedic dbHandlerMedic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);


        progress_bar = findViewById(R.id.progress_bar);
        text_after_progress = findViewById(R.id.text_after_progress);
        layout = findViewById(R.id.layout);
        layout2 = findViewById(R.id.layout2);
        button_main = findViewById(R.id.button_main);

        dbHandlerMedic = new DBHandlerMedic(PayOrderActivity.this);

        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PayOrderActivity.this, MainScreenActivity.class);
                startActivity(i);
                finish();
            }
        });

        text_after_progress.setText(getResources().getText(R.string.link_bank));

        handler.postDelayed(new Runnable() {
            public void run() {
                text_after_progress.setText(getResources().getText(R.string.pay_action));
            }
        }, 20000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dbHandlerMedic.ClearBasket();
                progress_bar.setVisibility(View.GONE);
                text_after_progress.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.VISIBLE);
            }
        },20000);
    }
}