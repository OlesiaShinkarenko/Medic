package com.example.medic.ProfileFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public class ProfileFragment extends Fragment {


    private static final String MY_SETTINGS = "my_settings_CreateCard";
    DBHandlerMedic dbHandlerMedic;
    private Context context;
    EditText editText_name;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getContext().getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        boolean hasSkipped = sp.getBoolean("hasSkipped",true);
        Intent i = new Intent(getContext(), CreateCardActivity.class);
        startActivity(i);
        if(hasSkipped){

        }
        context = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        editText_name = view.findViewById(R.id.editText_name);
        dbHandlerMedic = new DBHandlerMedic(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RetrofitClient.getRetrofitClient().GetProfile(dbHandlerMedic.getCardPatientId()).enqueue(new Callback<CardPatient>() {
                        @Override
                        public void onResponse(Call<CardPatient> call, Response<CardPatient> response) {
                            if (response.code() ==200){
                                Log.d("true","true");
                                CardPatient cardPatient = response.body();
                                editText_name.setText(cardPatient.getFirst_name());
                            }
                            else {
                                Toast.makeText(context, "fgvf", Toast.LENGTH_SHORT).show();
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

            return view;
    }
}