package com.example.medic.Onboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.medic.R;
import com.example.medic.Registration.RegistrationActivity;

import java.util.ArrayList;
import java.util.List;

public class OnBoardActivity extends AppCompatActivity {


    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout current_page;
    private ViewPager2 onboardViewPager;
    private  List<OnboardingItem> onboardingItems = new ArrayList<>();
    private TextView skip;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);


        current_page = findViewById(R.id.current_page);
        skip = findViewById(R.id.skip);

        setupOnboardingItems();

        onboardViewPager = findViewById(R.id.viewpager);
        onboardViewPager.setAdapter(onBoardingAdapter);


        setCurrentIndicator(0);
        onboardViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(OnBoardActivity.this, RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void setupOnboardingItems(){


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



    private void setCurrentIndicator(int index) {
        int childCount = current_page.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) current_page.getChildAt(i);
            if (i == index) {
                imageView.setImageResource(R.drawable.board_true);
            } else {
                imageView.setImageResource(R.drawable.board_false);
            }
        }

        if (index == onBoardingAdapter.getItemCount() - 1){
            skip.setText(String.valueOf(getResources().getText(R.string.complete)));
        }
       else {
                skip.setText(String.valueOf(getResources().getText(R.string.skip)));
    }

    }
}