package com.example.medic.SearchActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.example.medic.common.Analysis;
import com.example.medic.common.AnalysisResult;
import com.example.medic.common.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    TextView cancel;

    RecyclerView search_result;
    List<Analysis> filterList= new ArrayList<>();

    EditText search_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        cancel = findViewById(R.id.cancel);

        search_result = findViewById(R.id.search_result);

        search_edit_text = findViewById(R.id.search_edit_text);


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
                }else {
                    filterList.clear();
                    SearchAnalysisAdapter adapter = new SearchAnalysisAdapter(filterList,SearchActivity.this,s.toString());
                    search_result.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
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
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RetrofitClient.getRetrofitClient().searchAnalyses(search_edit_text.getText().toString()).enqueue(new Callback<AnalysisResult>() {
                        @Override
                        public void onResponse(Call<AnalysisResult> call, Response<AnalysisResult> response) {
                            if(response.isSuccessful()){
                                filterList = response.body().getAnalyses();
                                Log.d(filterList.get(0).getName(),filterList.get(0).getName());
                                SearchAnalysisAdapter adapter = new SearchAnalysisAdapter(filterList,SearchActivity.this,text);
                                search_result.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }else {
                                callAlertDialog(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<AnalysisResult> call, Throwable t) {
                            callAlertDialog(t.getMessage());
                        }
                    });
                }
            }).start();
        }catch (Exception e){
            callAlertDialog(e.getMessage());
        }

    }
    private void callAlertDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
        builder.setTitle("Ошибка!")
                .setMessage(message)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("Продолжить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем диалоговое окно
                        dialog.cancel();
                    }});
    }
}