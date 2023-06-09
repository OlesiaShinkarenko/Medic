package com.example.medic.AnalysisFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medic.R;
import com.example.medic.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AnalysisFragment extends Fragment {

    List<DiscountAndNews> discountAndNews = new ArrayList<>();

    RecyclerView recycle_view_banners;
    DiscountAdapter adapter;
    private Context context;

    public AnalysisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);
        context = this.getActivity();
        // Add the following lines to create RecyclerView
        recycle_view_banners = view.findViewById(R.id.recycle_view_banners);
        adapter = new DiscountAdapter(discountAndNews,context);
        recycle_view_banners.setAdapter(adapter);
        setData();
        return view;
    }

    private void setData() {
        RetrofitClient.getRetrofitClient().getDiscounts().enqueue(new Callback<List<DiscountAndNews>>() {
            @Override
            public void onResponse(Call<List<DiscountAndNews>> call, Response<List<DiscountAndNews>> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    discountAndNews = response.body();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<DiscountAndNews>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }
}