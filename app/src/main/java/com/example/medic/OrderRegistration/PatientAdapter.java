package com.example.medic.OrderRegistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {

    List<String> patients;
    private Context context;

    public PatientAdapter(List<String> patients, Context context) {
        this.patients = patients;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_patient,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        String patient = patients.get(position);
        holder.who_analysis.setText(patient);
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        List<String> patients;

        private TextView who_analysis;
        private ImageButton delete;
        private RecyclerView recycler_view_analysis;

        public ViewHolder(View itemView) {
            super(itemView);

            who_analysis = itemView.findViewById(R.id.who_analysis);
            delete = itemView.findViewById(R.id.delete);
            recycler_view_analysis = itemView.findViewById(R.id.recycler_view_analysis);
            delete.setVisibility(View.GONE);
            recycler_view_analysis.setVisibility(View.GONE);

            patients = new ArrayList<>();
            patients.add("gdfhfg");
            patients.add("w56yg");

            who_analysis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BottomSheetDialog dialog = new BottomSheetDialog(context);
                    dialog.setContentView(R.layout.add_patient);
                    dialog.show();
                    Button button_add_patient = dialog.findViewById(R.id.button_add_patient);
                   RecyclerView recycle_view_patient = dialog.findViewById(R.id.recycle_view_patient);
                   PatientCaseAdapter adapter = new PatientCaseAdapter(patients, context);
                   recycle_view_patient.setAdapter(adapter);
                  adapter.notifyDataSetChanged();
                  button_add_patient.setEnabled(true);
                       }
            });
        }
    }
}
