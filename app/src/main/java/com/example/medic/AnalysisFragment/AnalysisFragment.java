package com.example.medic.AnalysisFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.medic.BasketActivity.BasketActivity;
import com.example.medic.R;
import com.example.medic.SearchActivity.SearchActivity;
import com.example.medic.common.Analysis;
import com.example.medic.common.CategoriesAdapter;

import java.util.ArrayList;
import java.util.List;


public class AnalysisFragment extends Fragment   {

    List<DiscountAndNews> discountAndNews ;

    NestedScrollView scrollview;
    RelativeLayout basket_relativelayout;

    SwipeRefreshLayout swipe_refresh_layout;
    List<String> categories;
    List<Analysis> analyses;
    LinearLayout in_basket;
    EditText search_analysis;
    RecyclerView recycle_view_banners, recycle_view_catalog_name, recycle_view_catalog;
    DiscountAdapter adapter;
    TextView discount_and_news, catalog_analysis;

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
        recycle_view_catalog = view.findViewById(R.id.recycle_view_catalog);
        scrollview = view.findViewById(R.id.scrollview);
        discount_and_news = view.findViewById(R.id.discount_and_news);
        catalog_analysis = view.findViewById(R.id.catalog_analysis);
        swipe_refresh_layout = view.findViewById(R.id.swipe_refresh_layout);
        search_analysis = view.findViewById(R.id.search_analysis);
        basket_relativelayout = view.findViewById(R.id.basket_relativelayout);
        in_basket = view.findViewById(R.id.in_basket);

        discountAndNews = new ArrayList<>();
        categories = new ArrayList<>();
        analyses = new ArrayList<>();

        adapter = new DiscountAdapter(discountAndNews,context);
        recycle_view_banners.setAdapter(adapter);


        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories, context);
        recycle_view_catalog_name.setAdapter(categoriesAdapter);

        AnalysisAdapter analysisAdapter = new AnalysisAdapter(analyses,context);
        recycle_view_catalog.setAdapter(analysisAdapter);
        search_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SearchActivity.class);
                startActivity(i);
            }
        });
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData();
                swipe_refresh_layout.setRefreshing(false);
                adapter.notifyDataSetChanged();
                categoriesAdapter.notifyDataSetChanged();
                analysisAdapter.notifyDataSetChanged();
            }
        });
        setData();

        /*
        scrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY>recycle_view_banners.getScrollY()){
                    discount_and_news.setVisibility(View.GONE);
                    recycle_view_banners.setVisibility(View.GONE);
                    catalog_analysis.setVisibility(View.INVISIBLE);
                    basket_relativelayout.setVisibility(View.VISIBLE);
                }else {
                    discount_and_news.setVisibility(View.VISIBLE);
                    recycle_view_banners.setVisibility(View.VISIBLE);
                    catalog_analysis.setVisibility(View.VISIBLE);
                    basket_relativelayout.setVisibility(View.GONE);
                }
            }

        });

         */

        in_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BasketActivity.class);
                startActivity(i);
            }
        });
        return view;
    }


    private void setData() {
       /* RetrofitClient.getRetrofitClient().getDiscounts().enqueue(new Callback<List<DiscountAndNews>>() {
            @Override
            public void onResponse(Call<List<DiscountAndNews>> call, Response<List<DiscountAndNews>> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    discountAndNews = response.body();
                    adaptedsadr.notifyDataSetChanged();
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
        analyses.add(new Analysis("fgfg","fgfg","fgfdgf"));
            analyses.add(new Analysis("wq3re23","rfff","dhkulk"));
        analyses.add(new Analysis("345","fgffdg45g","fgfdfdsg"));
        analyses.add(new Analysis("345","fgffdg45g","fgfdfdsg"));
        analyses.add(new Analysis("345","fgffdg45g","fgfdfdsg"));
        analyses.add(new Analysis("345","fgffdg45g","fgfdfdsg"));
        analyses.add(new Analysis("345","fgffdg45g","fgfdfdsg"));
        analyses.add(new Analysis("345","fgffdg45g","fgfdfdsg"));
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }


}