package com.example.medic.OrderRegistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.example.medic.common.Analysis;
import com.example.medic.common.DBHandlerMedic;

import java.util.List;

public class PatientAnalysisAdapter extends RecyclerView.Adapter<PatientAnalysisAdapter.ViewHolder>{

    List<Analysis> analyses;
    DBHandlerMedic dbHandlerMedic;
    Context context;

    public PatientAnalysisAdapter(Context context) {
        dbHandlerMedic = new DBHandlerMedic(context);
        this.context = context;
        analyses = dbHandlerMedic.readAnalysis();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_analises,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Analysis analysis = analyses.get(position);
        holder.name_analysis.setText(analysis.getName());
        holder.price_analysis.setText(analysis.getPriceFormat());

    }

    @Override
    public int getItemCount() {
        return analyses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name_analysis,price_analysis;
        CheckBox check_box;

        public ViewHolder(View itemView) {
            super(itemView);
            name_analysis = itemView.findViewById(R.id.name_analysis);
            price_analysis = itemView.findViewById(R.id.price_analysis);
            check_box = itemView.findViewById(R.id.check_box);
            int pos = getAdapterPosition();
            check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (check_box.isChecked()){
                        name_analysis.setTextColor(context.getColor(R.color.black));
                        price_analysis.setTextColor(context.getColor(R.color.black));

                    }else {
                        name_analysis.setTextColor(context.getColor(R.color.AfternameOnBoard_color));
                        price_analysis.setTextColor(context.getColor(R.color.AfternameOnBoard_color));
                    } notifyItemChanged(pos);
                }
            });
        }
    }
}
