package com.example.medic.BasketActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.example.medic.common.Analysis;
import com.example.medic.common.DBHandlerMedic;

import java.util.List;

public class BasketAnalysisAdapter  extends RecyclerView.Adapter<BasketAnalysisAdapter.ViewHolder>{

    List<Analysis> analyses;
    DBHandlerMedic dbHandlerMedic;
    Context context;
    Integer kol_patient;

    BasketAnalysisAdapter.OnItemsCheckStateListener checkStateListener;
    public interface OnItemsCheckStateListener {
        void onItemCheckStateChanged();
    }
    public BasketAnalysisAdapter(List<Analysis> analyses, Context context) {
        this.context = context;
        this.analyses = analyses;
    }
    public void setOnItemsCheckStateListener(BasketAnalysisAdapter.OnItemsCheckStateListener checkStateListener) {
        this.checkStateListener = checkStateListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_basket, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Analysis analysis  = analyses.get(position);
        holder.name_analysis.setText(analysis.getName());
        holder.price_analysis.setText(analysis.getPriceFormat());
        dbHandlerMedic = new DBHandlerMedic(context);
        kol_patient = dbHandlerMedic.getPatient(analyses.get(position).getId());
        String s = holder.itemView.getResources().getQuantityString(R.plurals.patient, kol_patient,kol_patient);
        holder.kol_patient_analysis.setText(s);
    }

    @Override
    public int getItemCount() {
        return analyses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_analysis, price_analysis, kol_patient_analysis;
        ImageButton remove_analysis, remove_patient, add_patient;



        public ViewHolder(View itemView) {
            super(itemView);
            dbHandlerMedic = new DBHandlerMedic(context);
            name_analysis = itemView.findViewById(R.id.name_analysis);
            price_analysis = itemView.findViewById(R.id.price_analysis);
            kol_patient_analysis = itemView.findViewById(R.id.kol_patient_analysis);
            remove_analysis = itemView.findViewById(R.id.remove_analysis);
            remove_patient = itemView.findViewById(R.id.remove_patient);
            add_patient = itemView.findViewById(R.id.add_patient);



            remove_patient.setEnabled(false);

            add_patient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove_patient.setEnabled(true);
                    dbHandlerMedic.addPatient(analyses.get(getAdapterPosition()).getId());
                    kol_patient = dbHandlerMedic.getPatient(analyses.get(getAdapterPosition()).getId());
                    String s = itemView.getResources().getQuantityString(R.plurals.patient, kol_patient,kol_patient);
                    kol_patient_analysis.setText(s);
                    checkStateListener.onItemCheckStateChanged();
                }
            });
            remove_patient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(kol_patient>=2){
                        dbHandlerMedic.deletePatient(analyses.get(getAdapterPosition()).getId());
                        kol_patient = dbHandlerMedic.getPatient(analyses.get(getAdapterPosition()).getId());
                        String s = itemView.getResources().getQuantityString(R.plurals.patient, kol_patient,kol_patient);
                        kol_patient_analysis.setText(s);
                        checkStateListener.onItemCheckStateChanged();
                    }else{
                        remove_patient.setEnabled(false);
                    }
                }
            });


            remove_analysis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHandlerMedic.deleteAnalysis(analyses.get(getAdapterPosition()).getId());
                    analyses.remove(getAdapterPosition());
                    checkStateListener.onItemCheckStateChanged();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
