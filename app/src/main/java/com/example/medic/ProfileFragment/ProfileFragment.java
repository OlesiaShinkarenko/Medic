package com.example.medic.ProfileFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medic.CreateCard.CreateCardActivity;
import com.example.medic.MainScreen.MainScreenActivity;
import com.example.medic.R;
import com.example.medic.Registration.RegistrationActivity;

public class ProfileFragment extends Fragment {


    private static final String MY_SETTINGS = "my_settings_CreateCard";


    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getContext().getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        boolean hasSkipped = sp.getBoolean("hasSkipped",true);
        if(hasSkipped){
            Intent i = new Intent(getContext(), CreateCardActivity.class);
            startActivity(i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}