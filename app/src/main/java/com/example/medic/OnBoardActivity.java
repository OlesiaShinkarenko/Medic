package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OnBoardActivity extends AppCompatActivity {


    private  OnBoardingAdapter onBoardingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        setupOnboardingItems();

        ViewPager2 onboardViewPager = findViewById(R.id.viewpager);
        onboardViewPager.setAdapter(onBoardingAdapter);
    }

    private void setupOnboardingItems(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemAnalysis = new OnboardingItem();
        itemAnalysis.setImage(R.drawable.img_onboard1);
        itemAnalysis.setName(String.valueOf(getResources().getText(R.string.analysis)));
        itemAnalysis.setSubscription((String) getResources().getText(R.string.sample_collection));

        OnboardingItem itemNotifications = new OnboardingItem();
        itemNotifications.setImage(R.drawable.img_onboard2);
        itemNotifications.setName((String) getResources().getText(R.string.notifications));
        itemNotifications.setSubscription(String.valueOf(getResources().getText(R.string.quickly_result)));

        OnboardingItem itemMonitoring = new OnboardingItem();
        itemMonitoring.setImage(R.drawable.img_onboard3);
        itemMonitoring.setName(String.valueOf(getResources().getText(R.string.monitoring)));
        itemMonitoring.setSubscription(String.valueOf(getResources().getText(R.string.our_doctors)));


        onboardingItems.add(itemAnalysis);
        onboardingItems.add(itemNotifications);
        onboardingItems.add(itemMonitoring);

        onBoardingAdapter = new OnBoardingAdapter(onboardingItems);
    }
}