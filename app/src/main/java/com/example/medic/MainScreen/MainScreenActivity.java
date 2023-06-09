package com.example.medic.MainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.medic.AnalysisFragment.AnalysisFragment;
import com.example.medic.ProfileFragment.ProfileFragment;
import com.example.medic.R;
import com.example.medic.ResultFragment.ResultFragment;
import com.example.medic.Verification.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainScreenActivity extends AppCompatActivity {

    BottomNavigationView bottom_navigation_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        bottom_navigation_menu = findViewById(R.id.bottom_navigation_menu);



        AnalysisFragment analysisFragment = new AnalysisFragment();
        ResultFragment resultFragment = new ResultFragment();
        SupportFragment supportFragment = new SupportFragment();
        ProfileFragment profileFragment = new ProfileFragment();


        bottom_navigation_menu.setSelectedItemId(R.id.bottom_navigation_menu_analysis);
        bottom_navigation_menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_navigation_menu_analysis:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_fragment, analysisFragment)
                                .commit();
                        return true;
                    case R.id.bottom_navigation_menu_results:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_fragment, resultFragment)
                                .commit();
                        return true;
                    case R.id.bottom_navigation_menu_support:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_fragment, supportFragment)
                                .commit();
                        return true;
                    case R.id.bottom_navigation_menu_profile:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_fragment, profileFragment)
                                .commit();
                        return true;

                }
                return false;
            }
        });
    }
}