package com.example.medic.AnalysisFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.medic.R;

import java.util.ArrayList;
import java.util.List;


public class AnalysisFragment extends Fragment {

    List<DiscountAndNews> discountAndNews ;
    List<String> categories;

    RecyclerView recycle_view_banners, recycle_view_catalog_name;
    DiscountAdapter adapter;

    private Context context;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);
        context = this.getActivity();

        recycle_view_catalog_name = view.findViewById(R.id.recycle_view_catalog_name);
        recycle_view_banners = view.findViewById(R.id.recycle_view_banners);

        discountAndNews = new ArrayList<>();
        categories = new ArrayList<>();

        adapter = new DiscountAdapter(discountAndNews,context);

        recycle_view_banners.setAdapter(adapter);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories, context);
        recycle_view_catalog_name.setAdapter(categoriesAdapter);
        setData();
        return view;
    }

    private void setData() {
       /* RetrofitClient.getRetrofitClient().getDiscounts().enqueue(new Callback<List<DiscountAndNews>>() {
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
        */
        discountAndNews.add(new DiscountAndNews(1,"ff","fgfg","fgfg","fgfg"));
        discountAndNews.add(new DiscountAndNews(2,"gfddgs","fdg","34","df"));
        categories.add("kgoihjg");
        categories.add("ghfgh");
        categories.add("rewr");
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }
}