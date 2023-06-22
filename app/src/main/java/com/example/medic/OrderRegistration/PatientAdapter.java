package com.example.medic.OrderRegistration;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.example.medic.common.CardPatient;
import com.example.medic.common.DBHandlerMedic;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {

    List<CardPatient> patients = new ArrayList<>();
    private Context context;
    List<CardPatient> patients1;
    DBHandlerMedic dbHandlerMedic;

    public PatientAdapter(Context context) {
        this.context = context;
        dbHandlerMedic = new DBHandlerMedic(context);
        //присваеваем первое значение (профиль) в исходный "Кто будет сдавать анализы? "
        patients.add(dbHandlerMedic.readPatient().get(0));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.list_item_patient,parent,false);
        return new ViewHolder(view);
}

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        CardPatient patient = patients.get(position);
        holder.who_analysis.setText(patient.getFirst_name()+" "+patient.getLast_name());
        if(position!=0){
            //если позиция не нулевая, меняем вид
            holder.delete.setVisibility(View.VISIBLE);
            holder.recycler_view_analysis.setVisibility(View.VISIBLE);
            holder.constraint.setBackground(context.getDrawable(R.drawable.patient_analysis));
        }

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView who_analysis;
        private ImageButton delete;
        private RecyclerView recycler_view_analysis;
        Integer select;
        ConstraintLayout constraint;

        public ViewHolder(View itemView) {
            super(itemView);

            who_analysis = itemView.findViewById(R.id.who_analysis);
            delete = itemView.findViewById(R.id.delete);
            recycler_view_analysis = itemView.findViewById(R.id.recycler_view_analysis);
            constraint = itemView.findViewById(R.id.constraint);

            delete.setVisibility(View.GONE);
            recycler_view_analysis.setVisibility(View.GONE);

            PatientAnalysisAdapter patientAnalysisAdapter = new PatientAnalysisAdapter(context);
            recycler_view_analysis.setAdapter(patientAnalysisAdapter);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int select = getAdapterPosition();
                    if(patients.size()>1){
                        //удаляем элемент. по которому нажали
                        patients.remove(patients.get(select));
                        notifyItemRemoved(select);
                    }
                }
            });
            who_analysis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //открываем диалог
                    BottomSheetDialog dialog = new BottomSheetDialog(context);
                    dialog.setContentView(R.layout.add_patient);
                    dialog.show();

                    Button button_add_patient = dialog.findViewById(R.id.button_add_patient);
                   RecyclerView recycle_view_patient = dialog.findViewById(R.id.recycle_view_patient);

                    //окно появляется по клику на любой элемент "Кто будет сдавать анализы? "

                   PatientCaseAdapter adapter = new PatientCaseAdapter(patients,context);
                   adapter.setOnItemsCheckStateListener(new PatientCaseAdapter.OnItemsCheckStateListener() {
                       @Override
                       public void onItemCheckStateChanged(int selectedPos) {
                           if(selectedPos!= RecyclerView.NO_POSITION){
                               select = selectedPos;
                               button_add_patient.setEnabled(true);
                           }else{
                               button_add_patient.setEnabled(false);
                           }
                       }
                   });
                   recycle_view_patient.setAdapter(adapter);
                   dialog.findViewById(R.id.button_add_patient).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           dialog.dismiss();
                           delete.setVisibility(View.VISIBLE);
                           recycler_view_analysis.setVisibility(View.VISIBLE);
                           constraint.setBackground(context.getDrawable(R.drawable.patient_analysis));
                           //замена значений
                           if(patients.size()>1){
                               patients.remove(patients.get(select));
                           }else {
                               patients.clear();
                           }
                           patients.add(dbHandlerMedic.readPatient().get(select));
                           notifyDataSetChanged();
                       }
                   });
                }
            });
        }
    }
}
