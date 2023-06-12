package com.example.medic.AnalysisFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.example.medic.common.Analysis;

import java.util.List;

public class AnalysisAdapter extends RecyclerView.Adapter<AnalysisAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Analysis> analyses;

    public AnalysisAdapter(List<Analysis> analyses, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.analyses = analyses;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_analysis,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Analysis analysis = analyses.get(position);
        holder.name_analysis.setText(analysis.getName());
        holder.price_analysis.setText(analysis.getPrice());
        holder.time_analysis.setText(analysis.getTime_result());
    }

    @Override
    public int getItemCount() {
        return analyses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name_analysis, time_analysis,price_analysis;
        Button button_add;

        public ViewHolder(View itemView) {
            super(itemView);
            name_analysis = itemView.findViewById(R.id.name_analysis);
            time_analysis = itemView.findViewById(R.id.time_analysis);
            price_analysis = itemView.findViewById(R.id.price_analysis);
            button_add = itemView.findViewById(R.id.button_add);
            button_add.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
