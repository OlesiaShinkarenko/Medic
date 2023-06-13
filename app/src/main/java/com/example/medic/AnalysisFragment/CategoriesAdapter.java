package com.example.medic.AnalysisFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;

import java.util.List;

public class CategoriesAdapter  extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{

 private Context context;
    private int selectedPos = RecyclerView.NO_POSITION;
    private List<String> categories;

    public CategoriesAdapter(List<String> categories, Context context) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_catalog, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(categories.get(position));
        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.category);
            textView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            notifyItemChanged(selectedPos);
            selectedPos = getLayoutPosition();
            notifyItemChanged(selectedPos);
        }
    }
}
