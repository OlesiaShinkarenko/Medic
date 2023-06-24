package com.example.medic.OrderRegistration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.CreateCard.CreateCardActivity;
import com.example.medic.PayOrder.PayOrderActivity;
import com.example.medic.R;
import com.example.medic.common.Address;
import com.example.medic.common.CardPatient;
import com.example.medic.common.DBHandlerMedic;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderRegistrationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TextWatcher {

   private ImageButton btn_back;
    Integer select;
    private EditText edit_text_address, edit_text_datetime,date, edittext_number;
    private RecyclerView who_analysis;
    private BottomSheetDialog dialog;

    private DBHandlerMedic dbHandlerMedic;
    private Address address;
   private Button add_patient,registration;
   private ArrayList<CardPatient> patients;
    private List <String> timeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_registration);

        btn_back = findViewById(R.id.btn_back);
        edit_text_address = findViewById(R.id.edit_text_address);
        edit_text_datetime = findViewById(R.id.edit_text_datetime);
        who_analysis = findViewById(R.id.who_analysis);
        add_patient = findViewById(R.id.add_patient);
        edittext_number = findViewById(R.id.edittext_number);
        registration = findViewById(R.id.registration);

        dbHandlerMedic = new DBHandlerMedic(this);

        patients = dbHandlerMedic.readPatient();


        timeList = new ArrayList<>();

        timeList.add("10:00");
        timeList.add("13:00");
        timeList.add("13:00");
        timeList.add("14:00");
        timeList.add("15:00");
        timeList.add("16:00");
        timeList.add("17:00");

        dbHandlerMedic.addOrder();

        if(dbHandlerMedic.AddressExist()){
            address = dbHandlerMedic.getAddress();
            edit_text_address.setText(address.getAdd()+",кв. "+address.getFlat());
        }

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
                Button button_add = dialog.findViewById(R.id.button_add);
                EditText text_address = dialog.findViewById(R.id.edittext_address);
                EditText longitude_address = dialog.findViewById(R.id.longitude_address);
                EditText width_address = dialog.findViewById(R.id.width_address);
                EditText height_address = dialog.findViewById(R.id.height_address);
                EditText flat_address = dialog.findViewById(R.id.flat_address);
                EditText entrance_address = dialog.findViewById(R.id.entrance_address);
                EditText floor_address = dialog.findViewById(R.id.floor_address);
                EditText edittext_doorphone = dialog.findViewById(R.id.edittext_doorphone);
                EditText edittext_address_save = dialog.findViewById(R.id.edittext_address_save);
                SwitchCompat switch_save = dialog.findViewById(R.id.switch_save);

                if(dbHandlerMedic.AddressExist()){
                    edittext_address_save.setText(address.getLabel());
                    edittext_address_save.setVisibility(View.GONE);
                }

                if(address!=null){
                    text_address.setText(address.getAdd());
                    flat_address.setText(address.getFlat());
                    entrance_address.setText(address.getEntrance());
                    floor_address.setText(address.getFloor());
                    edittext_doorphone.setText(address.getDoorphone());
                    longitude_address.setText(address.getLongitude());
                    height_address.setText(address.getHeight());
                    width_address.setText(address.getWidth());
                }
                button_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         address = new Address(text_address.getText().toString(),
                                flat_address.getText().toString(),
                               entrance_address.getText().toString(),
                                floor_address.getText().toString(),
                                edittext_doorphone.getText().toString(),
                                edittext_address_save.getText().toString(),
                                 longitude_address.getText().toString(),
                                 height_address.getText().toString(),
                                 width_address.getText().toString());
                         if(switch_save.isChecked()){
                             dbHandlerMedic.addAddress(address);
                         }
                        dialog.dismiss();
                        edit_text_address.setText(address.getAdd()+",кв. "+address.getFlat());
                        dbHandlerMedic.setOrderAddress(edit_text_address.getText().toString());
                    }
                });

                switch_save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            edittext_address_save.setVisibility(View.VISIBLE);
                        }else {
                            edittext_address_save.setVisibility(View.GONE);
                        }
                    }
                });
                TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(text_address.getText().length() == 0||
                        flat_address.getText().length()==0||
                        entrance_address.getText().length()==0||
                        floor_address.getText().length()==0
                                ||longitude_address.getText().length() ==0||
                                width_address.getText().length() ==0||
                                height_address.getText().length() ==0
                                ||edittext_doorphone.getText().length()==0){
                            button_add.setEnabled(false);
                        }else{
                            button_add.setEnabled(true);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                };
                text_address.addTextChangedListener(textWatcher);
                 longitude_address.addTextChangedListener(textWatcher);
                 width_address.addTextChangedListener(textWatcher);
               height_address.addTextChangedListener(textWatcher);
               flat_address.addTextChangedListener(textWatcher);
              entrance_address.addTextChangedListener(textWatcher);
              floor_address.addTextChangedListener(textWatcher);
                edittext_doorphone.addTextChangedListener(textWatcher);
            }
        });



        edit_text_datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new BottomSheetDialog(OrderRegistrationActivity.this);
                dialog.setContentView(R.layout.date_time);
                dialog.show();
                RecyclerView recyclerView = dialog.findViewById(R.id.time_recycle_view);
                Button button_con_date = dialog.findViewById(R.id.button_add);
                recyclerView.setLayoutManager(new GridLayoutManager(OrderRegistrationActivity.this, 4));
                TimeAdapter timeAdapter = new TimeAdapter(timeList,OrderRegistrationActivity.this);
                dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                timeAdapter.setOnItemsCheckStateListener(new TimeAdapter.OnItemsCheckStateListener() {
                    @Override
                    public void onItemCheckStateChanged(int selectedPos) {
                        if(selectedPos!= RecyclerView.NO_POSITION){
                            button_con_date.setEnabled(true);
                        }else{
                            button_con_date.setEnabled(false);
                        }
                    }
                });
                recyclerView.setAdapter(timeAdapter);
                button_con_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        edit_text_datetime.setText(date.getText().toString());
                        dbHandlerMedic.setDateTimeOrder(edit_text_datetime.getText().toString());
                    }
                });

                date = dialog.findViewById(R.id.date);
                date.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(date.length()==0){
                            button_con_date.setEnabled(false);
                        }
                    }
                });
                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DataPicker dataPicker;
                        dataPicker = new DataPicker();
                        dataPicker.show(getSupportFragmentManager(),"DATE PICK");
                    }
                });
            }
        });
        edit_text_address.addTextChangedListener(this);
        edit_text_datetime.addTextChangedListener(this);
        edittext_number.addTextChangedListener(this);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderRegistrationActivity.this, PayOrderActivity.class);
                startActivity(i);
                finish();
            }
        });
        PatientAdapter patientAdapter = new PatientAdapter(this);
        who_analysis.setAdapter(patientAdapter);
        add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вызываем диалог добавления пациента
                dialog = new BottomSheetDialog(OrderRegistrationActivity.this);
                dialog.setContentView(R.layout.add_patient);
                dialog.show();
                Button button_add_patient = dialog.findViewById(R.id.button_add_patient);
                RecyclerView recycle_view_patient = dialog.findViewById(R.id.recycle_view_patient);
                List<CardPatient> patients1 =new ArrayList<>();
                for(CardPatient cardPatient:patients){
                    if(!dbHandlerMedic.PatientAlreadyInOrder(cardPatient.getId())){
                        patients1.add(cardPatient);
                    }
                }
                //добавляем ему адаптер
                PatientCaseAdapter adapter = new PatientCaseAdapter(patients,OrderRegistrationActivity.this);
                adapter.setOnItemsCheckStateListener(new PatientCaseAdapter.OnItemsCheckStateListener() {
                    @Override
                    public void onItemCheckStateChanged(int selectedPos) {
                        //если ничего не выбрано, кнопка недоступна
                        if(selectedPos!= RecyclerView.NO_POSITION){
                            select = selectedPos;
                            button_add_patient.setEnabled(true);

                        }else{
                            button_add_patient.setEnabled(false);
                        }
                    }
                });
                recycle_view_patient.setAdapter(adapter);
                Button add_patient_patient_case = dialog.findViewById(R.id.add_patient_patient_case);
                //добавляем переход к созданию карты, если выбрана кнопка "Добавить пациента"
                add_patient_patient_case.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(OrderRegistrationActivity.this, CreateCardActivity.class);
                        i.putExtra("how",true);
                        startActivity(i);
                    }
                });
                button_add_patient.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        patientAdapter.patients.add(patients1.get(select));
                        adapter.notifyDataSetChanged();
                        patientAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        date.setText(selectedDate);
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(edit_text_address.getText().toString().length() == 0||edit_text_datetime.getText().toString().length()==0||
                edittext_number.getText().toString().length()<11){
            registration.setEnabled(false);
        }else{
            registration.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}