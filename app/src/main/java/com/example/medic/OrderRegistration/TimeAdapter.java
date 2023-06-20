package com.example.medic.OrderRegistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    List<String> time;
    private int selectedPos = RecyclerView.NO_POSITION;
    LayoutInflater layoutInflater;
    TimeAdapter.OnItemsCheckStateListener checkStateListener;

    public interface OnItemsCheckStateListener {
        void onItemCheckStateChanged(int selectedPos);
    }
    public void setOnItemsCheckStateListener(TimeAdapter.OnItemsCheckStateListener checkStateListener) {
        this.checkStateListener = checkStateListener;
    }

    public TimeAdapter(List<String> time, Context context) {
        this.time = time;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_catalog,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeAdapter.ViewHolder holder, int position) {
        holder.category.setText(time.get(position));
        holder.itemView.setSelected(selectedPos == position);
        checkStateListener.onItemCheckStateChanged(selectedPos);
    }

    @Override
    public int getItemCount() {
        return time.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView category;

        public ViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
            category.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            notifyItemChanged(selectedPos);
            selectedPos = getLayoutPosition();
            notifyItemChanged(selectedPos);
        }
    }
}
