package com.example.medic.OrderRegistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.example.medic.common.CardPatient;

import java.util.List;

public class PatientCaseAdapter extends RecyclerView.Adapter<PatientCaseAdapter.ViewHolder> {

    List<CardPatient> patients;
    private int selectedPos = RecyclerView.NO_POSITION;
    Context context;
    OnItemsCheckStateListener checkStateListener;

    public interface OnItemsCheckStateListener {
        void onItemCheckStateChanged(int selectedPos);
    }
    public void setOnItemsCheckStateListener(OnItemsCheckStateListener checkStateListener) {
        this.checkStateListener = checkStateListener;
    }
    public PatientCaseAdapter(List<CardPatient>patients,Context context) {
        this.context = context;
        this.patients = patients;
    }

    @Override
    public PatientCaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_patient_add,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        CardPatient patient = patients.get(position);
        holder.patient.setText(patient.getFirst_name()+" "+patient.getLast_name());
        holder.itemView.setSelected(selectedPos == position);
    }


    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView patient;

        public ViewHolder(View itemView) {
            super(itemView);
            patient = itemView.findViewById(R.id.patient);
            patient.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            notifyItemChanged(selectedPos);
            selectedPos = getLayoutPosition();
            notifyItemChanged(selectedPos);
            checkStateListener.onItemCheckStateChanged(selectedPos);
        }
    }
}
