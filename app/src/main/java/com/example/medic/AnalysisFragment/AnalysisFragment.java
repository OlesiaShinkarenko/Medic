package com.example.medic.AnalysisFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medic.R;

import java.util.ArrayList;


public class AnalysisFragment extends Fragment {

    ArrayList<DiscountAndNews> discountAndNews = new ArrayList<>();

    RecyclerView recycle_view_banners;
    public AnalysisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        recycle_view_banners = view.findViewById(R.id.recycle_view_banners);
        setData();
        DiscountAdapter adapter = new DiscountAdapter(discountAndNews,getActivity().getApplicationContext());
        recycle_view_banners.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }

    private void setData() {
        discountAndNews.add(new DiscountAndNews(getString(R.string.name_banners),getString(R.string.subscription_banners),R.drawable.banner_img));
        discountAndNews.add(new DiscountAndNews(getString(R.string.name_banners2),getString(R.string.subscription_banners2),R.drawable.banner_img));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_analysis, container, false);
    }
}