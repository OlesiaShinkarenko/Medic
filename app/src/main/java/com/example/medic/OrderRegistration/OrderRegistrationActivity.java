package com.example.medic.OrderRegistration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class OrderRegistrationActivity extends AppCompatActivity {

    ImageButton btn_back;
    EditText edit_text_address, edit_text_datetime;
    RecyclerView who_analysis;
    BottomSheetDialog dialog;
    Button add_patient;
    List<String> patients;
    List <String> timeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_registration);

        btn_back = findViewById(R.id.btn_back);
        edit_text_address = findViewById(R.id.edit_text_address);
        edit_text_datetime = findViewById(R.id.edit_text_datetime);
        who_analysis = findViewById(R.id.who_analysis);
        add_patient = findViewById(R.id.add_patient);

        timeList = new ArrayList<>();

        timeList.add("10:00");
        timeList.add("13:00");
        timeList.add("13:00");
        timeList.add("14:00");
        timeList.add("15:00");
        timeList.add("16:00");
        timeList.add("17:00");


        patients = new ArrayList<>();
        patients.add("kglfkg");

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
              /*  CategoriesAdapter adapter = new CategoriesAdapter(timeList,OrderRegistrationActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

               */
            }
        });

        PatientAdapter patientAdapter = new PatientAdapter(patients,this);
        who_analysis.setAdapter(patientAdapter);

        add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new BottomSheetDialog(OrderRegistrationActivity.this);
                dialog.setContentView(R.layout.add_patient);
                dialog.show();
                Button button_add_patient = dialog.findViewById(R.id.button_add_patient);
                RecyclerView recycle_view_patient = dialog.findViewById(R.id.recycle_view_patient);
                PatientCaseAdapter adapter = new PatientCaseAdapter(patients, OrderRegistrationActivity.this);
                adapter.setOnItemsCheckStateListener(new PatientCaseAdapter.OnItemsCheckStateListener() {
                    @Override
                    public void onItemCheckStateChanged(int selectedPos) {
                        if(selectedPos!= RecyclerView.NO_POSITION){
                            button_add_patient.setEnabled(true);
                        }else{
                            button_add_patient.setEnabled(false);
                        }
                    }
                });
                recycle_view_patient.setAdapter(adapter);
            }
        });
    }
}