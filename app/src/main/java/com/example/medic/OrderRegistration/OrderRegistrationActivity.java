package com.example.medic.OrderRegistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.medic.AnalysisFragment.CategoriesAdapter;
import com.example.medic.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class OrderRegistrationActivity extends AppCompatActivity {

    ImageButton btn_back;
    EditText edit_text_address, edit_text_datetime;
    BottomSheetDialog dialog;
    List <String> timeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_registration);

        btn_back = findViewById(R.id.btn_back);
        edit_text_address = findViewById(R.id.edit_text_address);
        edit_text_datetime = findViewById(R.id.edit_text_datetime);

        timeList = new ArrayList<>();

        timeList.add("10:00");
        timeList.add("13:00");
        timeList.add("13:00");
        timeList.add("14:00");
        timeList.add("15:00");
        timeList.add("16:00");
        timeList.add("17:00");

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
                dialog.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        edit_text_datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new BottomSheetDialog(OrderRegistrationActivity.this);
                dialog.setContentView(R.layout.date_time);
                dialog.show();
                dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                RecyclerView recyclerView = dialog.findViewById(R.id.time_recycle_view);
                recyclerView.setLayoutManager(new GridLayoutManager(OrderRegistrationActivity.this, 4));
                CategoriesAdapter adapter = new CategoriesAdapter(timeList,OrderRegistrationActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}