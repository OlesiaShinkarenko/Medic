package com.example.medic.AnalysisFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.medic.BasketActivity.BasketActivity;
import com.example.medic.R;
import com.example.medic.SearchActivity.SearchActivity;
import com.example.medic.common.Analysis;
import com.example.medic.common.AnalysisResult;
import com.example.medic.common.DBHandlerMedic;
import com.example.medic.common.DiscountAndNews;
import com.example.medic.common.NewsResult;
import com.example.medic.common.RetrofitClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AnalysisFragment extends Fragment   {

    List<DiscountAndNews> discountAndNews ;
    List<Analysis> analysis_in_categories;

    NestedScrollView scrollview;
    RelativeLayout basket_relativelayout;
    CategoriesAdapter categoriesAdapter;

    SwipeRefreshLayout swipe_refresh_layout;
    List<Categories> categories;
    static List<Analysis> analyses= new ArrayList<>();
    static List<Analysis> fullanalyses= new ArrayList<>();
    LinearLayout in_basket;
    EditText search_analysis;
    RecyclerView recycle_view_banners, recycle_view_catalog_name, recycle_view_catalog;
    DiscountAdapter discountAdapter;
    AnalysisAdapter analysisAdapter;
    String substitutedString;
    TextView discount_and_news, catalog_analysis , sum_basket;
    DBHandlerMedic dbHandlerMedic;

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
        sum_basket = view.findViewById(R.id.sum_basket);

        search_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SearchActivity.class);
                i.putExtra("analysis", (Serializable) fullanalyses);
                startActivity(i);
            }
        });
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData();
                swipe_refresh_layout.setRefreshing(false);
            }
        });
        setData();
        dbHandlerMedic = new DBHandlerMedic(context);
        setSum_basket();
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
                i.putExtra("analysis",(Serializable) fullanalyses);
                startActivity(i);
            }
        });
        return view;
    }


    private void setData() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   RetrofitClient.getRetrofitClient().getAnalyses().enqueue(new Callback<AnalysisResult>() {
                       @Override
                       public void onResponse(Call<AnalysisResult> call, Response<AnalysisResult> response) {
                               analyses = response.body().getAnalyses();
                               fullanalyses = new ArrayList<>();
                           fullanalyses.addAll(analyses);
                           analysisAdapter = new AnalysisAdapter(analyses,context);
                           analysisAdapter.setOnItemsCheckStateListener(new AnalysisAdapter.OnItemsCheckStateListener(){

                               @Override
                               public void onItemCheckStateChanged() {
                                   setSum_basket();
                               }
                           });
                           recycle_view_catalog.setAdapter(analysisAdapter);
                       }
                       @Override
                       public void onFailure(Call<AnalysisResult> call, Throwable t) {
                       }
                   });

                   RetrofitClient.getRetrofitClient().getCategories().enqueue(new Callback<CategoriesResult>() {
                       @Override
                       public void onResponse(Call<CategoriesResult> call, Response<CategoriesResult> response) {
                           categories = response.body().getCategories();
                           categoriesAdapter = new CategoriesAdapter(categories, context);
                           categoriesAdapter.setOnItemsCheckStateListener(new CategoriesAdapter.OnItemsCheckStateListener() {
                               @Override
                               public void onItemCheckStateChanged(int category) {
                                   if (category != -1 && analyses != null) {
                                       analyses.clear();
                                       analyses.addAll(fullanalyses);
                                       analysis_in_categories = new ArrayList<>();
                                       for (Analysis analysis : analyses) {
                                           if (analysis.getCategory() == category) {
                                               analysis_in_categories.add(analysis);
                                           }
                                       }
                                       analyses.clear();
                                       analyses.addAll(analysis_in_categories);
                                    analysisAdapter.notifyDataSetChanged();
                                   }
                               }
                           });
                           recycle_view_catalog_name.setAdapter(categoriesAdapter);
                       }

                       @Override
                       public void onFailure(Call<CategoriesResult> call, Throwable t) {

                       }
                   });
               }catch (Exception e){
               }

               RetrofitClient.getRetrofitClient().getNews().enqueue(new Callback<NewsResult>() {
                   @Override
                   public void onResponse(Call<NewsResult> call, Response<NewsResult> response) {
                       discountAndNews = response.body().getResults();
                       discountAdapter = new DiscountAdapter(discountAndNews,context);
                       recycle_view_banners.setAdapter(discountAdapter);
                   }

                   @Override
                   public void onFailure(Call<NewsResult> call, Throwable t) {

                   }
               });
           }
       }).start();

    }

    private void setSum_basket(){
        int sum = dbHandlerMedic.getSumPrice();
        if (sum>0){
            String javaFormatString  = "%d ₽";
            substitutedString  =  String.format(javaFormatString, sum);
            sum_basket.setText(substitutedString);
            basket_relativelayout.setVisibility(View.VISIBLE);}else {
            basket_relativelayout.setVisibility(View.GONE);
        }
    }

}