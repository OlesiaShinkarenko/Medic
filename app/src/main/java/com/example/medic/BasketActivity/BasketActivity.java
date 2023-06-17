package com.example.medic.BasketActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.MainScreen.MainScreenActivity;
import com.example.medic.OrderRegistration.OrderRegistrationActivity;
import com.example.medic.R;
import com.example.medic.common.Analysis;
import com.example.medic.common.DBHandlerMedic;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    ImageButton btn_back, icon_basket_delete;

    List<Analysis> analyses = new ArrayList<>();
    RecyclerView recycler_view_basket;
    Button transition;
    DBHandlerMedic dbHandlerMedic;
    TextView sum_analysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        btn_back = findViewById(R.id.btn_back);
        icon_basket_delete = findViewById(R.id.icon_basket_delete);
        recycler_view_basket = findViewById(R.id.recycler_view_basket);
        transition = findViewById(R.id.transition);
        sum_analysis= findViewById(R.id.sum_analysis);

        dbHandlerMedic = new DBHandlerMedic(BasketActivity.this);
        analyses = dbHandlerMedic.readAnalysis();

        int sum = dbHandlerMedic.getSumPrice();
        String javaFormatString  = "%d ₽";
        String substitutedString  =  String.format(javaFormatString, sum);
        sum_analysis.setText(substitutedString);

        BasketAnalysisAdapter adapter = new BasketAnalysisAdapter(analyses, this);
        adapter.setOnItemsCheckStateListener(new BasketAnalysisAdapter.OnItemsCheckStateListener() {
            @Override
            public void onItemCheckStateChanged() {
                int sum = dbHandlerMedic.getSumPrice();
                    String javaFormatString  = "%d ₽";
                   String substitutedString  =  String.format(javaFormatString, sum);
                sum_analysis.setText(substitutedString);

            }
        });
        recycler_view_basket.setAdapter(adapter);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BasketActivity.this, MainScreenActivity.class);
                startActivity(i);
                finish();
            }
        });


        icon_basket_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandlerMedic.deleteAllAnalysis();
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