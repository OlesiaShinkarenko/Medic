package com.example.medic.SearchActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.MainScreen.MainScreenActivity;
import com.example.medic.R;
import com.example.medic.common.Analysis;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    TextView cancel;

    RecyclerView search_result;
    List<Analysis> analyses;
    List<Analysis> filterList= new ArrayList<>();

    EditText search_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        cancel = findViewById(R.id.cancel);

        search_result = findViewById(R.id.search_result);

        search_edit_text = findViewById(R.id.search_edit_text);

        analyses = (ArrayList)getIntent().getSerializableExtra("analysis");

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList.clear();
                if (s.length()>=3){
                    if(s.toString().isEmpty()){
                        SearchAnalysisAdapter adapter = new SearchAnalysisAdapter(filterList,SearchActivity.this,s.toString());
                        search_result.setAdapter(adapter);
                    }else {
                        Filter(s.toString());
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void Filter(String text) {

        for(Analysis analysis:analyses){
            if(analysis.getName().contains(text)){
                filterList.add(analysis);
            }
        }
        SearchAnalysisAdapter adapter = new SearchAnalysisAdapter(filterList,SearchActivity.this,text);
        search_result.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}