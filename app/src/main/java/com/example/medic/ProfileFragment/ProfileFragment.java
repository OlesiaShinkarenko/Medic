package com.example.medic.ProfileFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.medic.CreateCard.CreateCardActivity;
import com.example.medic.R;
import com.example.medic.common.CardPatient;
import com.example.medic.common.DBHandlerMedic;
import com.example.medic.common.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements TextWatcher {


    private static final String MY_SETTINGS = "my_settings_CreateCard";
    DBHandlerMedic dbHandlerMedic;
    private Context context;
    Spinner spinner_gender;
    CardPatient cardPatient;
    Button btn_create_card;
    EditText editText_first_name,editText_lastname,editText_middle_name,editText_date_birthday;
    private static final String SETTING_ACCESS= "setting_refresh";
    private static final String ACCESS = "refresh";
    SharedPreferences sharedPreferences;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getContext().getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        boolean hasSkipped = sp.getBoolean("hasSkipped",true);
        if(hasSkipped){
            Intent i = new Intent(getContext(), CreateCardActivity.class);
            startActivity(i);
        }
        context = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        editText_first_name = view.findViewById(R.id.editText_first_name);
        editText_lastname = view.findViewById(R.id.editText_lastname);
        editText_middle_name = view.findViewById(R.id.editText_middle_name);
        editText_date_birthday = view.findViewById(R.id.editText_date_birthday);
        spinner_gender = view.findViewById(R.id.spinner_gender);
        btn_create_card = view.findViewById(R.id.btn_create_card);

        dbHandlerMedic = new DBHandlerMedic(context);
        sharedPreferences = context.getSharedPreferences(SETTING_ACCESS,Context.MODE_PRIVATE);
        String access = "Bearer "+sharedPreferences.getString(ACCESS,"");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RetrofitClient.getRetrofitClient().GetProfile(access,dbHandlerMedic.getCardPatientId()).enqueue(new Callback<CardPatient>() {
                        @Override
                        public void onResponse(Call<CardPatient> call, Response<CardPatient> response) {
                            if (response.isSuccessful()){
                                cardPatient = response.body();
                                editText_first_name.setText(cardPatient.getFirst_name());
                                editText_lastname.setText(cardPatient.getLast_name());
                                editText_middle_name.setText(cardPatient.getMiddle_name());
                                editText_date_birthday.setText(cardPatient.getDate_of_birth());
                                spinner_gender.setSelection(Integer.valueOf(cardPatient.getPol()),false);
                                btn_create_card.setEnabled(false);
                            }
                            else {
                                Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CardPatient> call, Throwable t) {

                        }
                    });
                }catch (Exception ex){
                }
            }
        }).start();

        editText_first_name.addTextChangedListener(this);
        editText_lastname.addTextChangedListener(this);
        editText_middle_name.addTextChangedListener(this);
        editText_date_birthday.addTextChangedListener(this);
            return view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(editText_first_name.getText().toString().equals(cardPatient.getFirst_name())
                &&editText_middle_name.getText().toString().equals(cardPatient.getMiddle_name())
                &&editText_lastname.getText().toString().equals(cardPatient.getLast_name())
                &&editText_date_birthday.getText().toString().equals(cardPatient.getDate_of_birth())){
            btn_create_card.setEnabled(false);
        }else {
            btn_create_card.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}