package com.example.medic.BasketActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.medic.OrderRegistration.OrderRegistrationActivity;
import com.example.medic.R;
import com.example.medic.common.Analysis;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    ImageButton btn_back, icon_basket_delete;

    List<Analysis> analyses = new ArrayList<>();
    RecyclerView recycler_view_basket;
    Button transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        btn_back = findViewById(R.id.btn_back);
        icon_basket_delete = findViewById(R.id.icon_basket_delete);
        recycler_view_basket = findViewById(R.id.recycler_view_basket);
        transition = findViewById(R.id.transition);

        BasketAnalysisAdapter adapter = new BasketAnalysisAdapter(analyses, this);
        recycler_view_basket.setAdapter(adapter);
        analyses.add(new Analysis("345","fgffdg45g","fgfdfdsg"));
        analyses.add(new Analysis("345","fgffdg45g","fgfdfdsg"));
        analyses.add(new Analysis("345","fgffdg45g","fgfdfdsg"));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        icon_basket_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analyses.clear();
                adapter.notifyDataSetChanged();
            }
        });

        transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BasketActivity.this, OrderRegistrationActivity.class);
                startActivity(i);
            }
        });
    }
}