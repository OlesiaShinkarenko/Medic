package com.example.medic.BasketActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.example.medic.common.Analysis;

import java.util.List;

public class BasketAnalysisAdapter  extends RecyclerView.Adapter<BasketAnalysisAdapter.ViewHolder>{

    List<Analysis> analyses;

    LayoutInflater layoutInflater;

    public BasketAnalysisAdapter(List<Analysis> analyses, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.analyses = analyses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_basket, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Analysis analysis  = analyses.get(position);
        holder.name_analysis.setText(analysis.getName());
        holder.price_analysis.setText(analysis.getPrice());
    }

    @Override
    public int getItemCount() {
        return analyses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_analysis, price_analysis, kol_patient_analysis;
        ImageButton remove_analysis, remove_patient, add_patient;
        Integer kol_patient = 1;
        BasketAnalysisAdapter adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            name_analysis = itemView.findViewById(R.id.name_analysis);
            price_analysis = itemView.findViewById(R.id.price_analysis);
            kol_patient_analysis = itemView.findViewById(R.id.kol_patient_analysis);
            remove_analysis = itemView.findViewById(R.id.remove_analysis);
            remove_patient = itemView.findViewById(R.id.remove_patient);
            add_patient = itemView.findViewById(R.id.add_patient);

            String s = itemView.getResources().getQuantityString(R.plurals.patient, kol_patient,kol_patient);
            kol_patient_analysis.setText(s);
            remove_patient.setEnabled(false);

            add_patient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove_patient.setEnabled(true);
                    kol_patient++;
                    String s = itemView.getResources().getQuantityString(R.plurals.patient, kol_patient,kol_patient);
                    kol_patient_analysis.setText(s);
                }
            });
            remove_patient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(kol_patient>=2){
                        kol_patient--;
                        String s = itemView.getResources().getQuantityString(R.plurals.patient, kol_patient,kol_patient);
                        kol_patient_analysis.setText(s);
                    }else{
                        remove_patient.setEnabled(false);
                    }
                }
            });


            remove_analysis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    analyses.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
