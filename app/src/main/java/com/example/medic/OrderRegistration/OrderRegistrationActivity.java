package com.example.medic.OrderRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.medic.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class OrderRegistrationActivity extends AppCompatActivity {

    ImageButton btn_back;
    EditText edit_text_address;
    BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_registration);

        btn_back = findViewById(R.id.btn_back);
        edit_text_address = findViewById(R.id.edit_text_address);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        edit_text_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new BottomSheetDialog(OrderRegistrationActivity.this);
                dialog.setContentView(R.layout.delivery_address);
                dialog.show();
            }
        });
    }
}