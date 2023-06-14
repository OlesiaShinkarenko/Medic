package com.example.medic.OrderRegistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;

import java.util.List;

public class PatientCaseAdapter extends RecyclerView.Adapter<PatientCaseAdapter.ViewHolder> {

    List<String> patients;
    private int selectedPos = RecyclerView.NO_POSITION;
    Context context;
    OnItemsCheckStateListener checkStateListener;

    public interface OnItemsCheckStateListener {
        void onItemCheckStateChanged(int selectedPos);
    }
    public void setOnItemsCheckStateListener(OnItemsCheckStateListener checkStateListener) {
        this.checkStateListener = checkStateListener;
    }
    public PatientCaseAdapter(List<String> patients, Context context) {
        this.patients = patients;
        this.context = context;
    }

    @Override
    public PatientCaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_patient_add,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        String patient = patients.get(position);
        holder.patient.setText(patient);
        holder.itemView.setSelected(selectedPos == position);
        checkStateListener.onItemCheckStateChanged(selectedPos);
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
        }
    }
}
