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
import com.example.medic.common.Order;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class AnalysisAdapter extends RecyclerView.Adapter<AnalysisAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Analysis> analyses;
    private Context context;

    private Integer selectedPos;

    AnalysisAdapter.OnItemsCheckStateListener checkStateListener;

    public interface OnItemsCheckStateListener {
        void onItemCheckStateChanged(int price_analysis);
    }
    public void setOnItemsCheckStateListener(AnalysisAdapter.OnItemsCheckStateListener checkStateListener) {
        this.checkStateListener = checkStateListener;
    }

    public AnalysisAdapter(List<Analysis> analyses, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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
      if (!Order.id.contains(analysis.getId())){
            holder.button_add.setText(R.string.add_analysis);
            holder.button_add.setSelected(false);
        }else
        {
            holder.button_add.setText(R.string.delete);
            holder.button_add.setSelected(true);
        }
    }


    @Override
    public int getItemCount() {
        return analyses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name_analysis, time_analysis,price_analysis;
        Button button_add;
        BottomSheetDialog dialog;

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
            selectedPos=getAdapterPosition();;
           if(!Order.id.contains(analyses.get(selectedPos).getId())){
                dialog = new BottomSheetDialog(context);
                dialog.setContentView(R.layout.card_product);
                dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button_add.setText(itemView.getResources().getText(R.string.delete));
                        Order.id.add(analyses.get(selectedPos).getId());
                        button_add.setSelected(true);
                        checkStateListener.onItemCheckStateChanged(analyses.get(selectedPos).getPrice_int());
                        dialog.dismiss();
                    }
                });
                TextView name_analysis = dialog.findViewById(R.id.name_analysis);
                name_analysis.setText(analyses.get(selectedPos).getName());
                TextView description_analysis = dialog.findViewById(R.id.description_analysis);
                description_analysis.setText(analyses.get(selectedPos).getDescription());
                TextView preparation_analysis = dialog.findViewById(R.id.preparation_analysis);
                preparation_analysis.setText(analyses.get(selectedPos).getPreparation());
                TextView result_analysis = dialog.findViewById(R.id.result_analysis);
                result_analysis.setText(analyses.get(selectedPos).getTime_result());
                TextView bio_analysis = dialog.findViewById(R.id.bio_analysis);
                bio_analysis.setText(analyses.get(selectedPos).getBio());
                Button button_add = dialog.findViewById(R.id.button_add);

                String javaFormatString  =  context.getString(R.string.add_with);
               String  substitutedString  =  String.format(javaFormatString,analyses.get(selectedPos).getPrice());
                button_add.setText(substitutedString);
                dialog.show();
            }else {
                Order.id.remove(analyses.get(selectedPos).getId());
                checkStateListener.onItemCheckStateChanged(-analyses.get(selectedPos).getPrice_int());
                button_add.setSelected(false);
                button_add.setText(itemView.getResources().getText(R.string.add_analysis));
            }
        }
    }
}
