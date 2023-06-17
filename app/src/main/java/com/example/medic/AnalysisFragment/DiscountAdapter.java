package com.example.medic.AnalysisFragment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medic.R;
import com.example.medic.common.DiscountAndNews;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.ViewHolder>{

    private final List<DiscountAndNews> discountAndNews;
    private Context context;

    public DiscountAdapter(List<DiscountAndNews> discountAndNews, Context context) {
        this.context = context;
        this.discountAndNews = discountAndNews;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_banners,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiscountAndNews discountAndNews1 = discountAndNews.get(position);
        holder.nameView.setText(discountAndNews1.getName());
        holder.descriptionView.setText(discountAndNews1.getDescription());
        holder.priceView.setText(discountAndNews1.getPrice());
        Glide.with(context).load(discountAndNews1.getImage()).into(holder.image_banners);
    }

    @Override
    public int getItemCount() {
        return discountAndNews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameView, descriptionView, priceView;
        private ImageView image_banners;

        public ViewHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.name_banner);
            descriptionView = itemView.findViewById(R.id.description_banner);
            priceView = itemView.findViewById(R.id.price_banner);
            image_banners = itemView.findViewById(R.id.image_banners);
        }
    }
}
