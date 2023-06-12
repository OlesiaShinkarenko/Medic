package com.example.medic.SearchActivity;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.common.Analysis;
import com.example.medic.R;

import java.util.List;
import java.util.Locale;

public class SearchAnalysisAdapter extends RecyclerView.Adapter<SearchAnalysisAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<Analysis> analyses;
    private String searchTerm;

    public SearchAnalysisAdapter(List<Analysis> analyses, Context context,String searchTerm) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.analyses = analyses;
        this.searchTerm = searchTerm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_search,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Analysis analysis = analyses.get(position);
        holder.name_analysis.setText(analysis.getName());
        holder.price_analysis.setText(analysis.getPrice());
        holder.time_analysis.setText(analysis.getTime_result());
        String displayName = analysis.getName();
        int startIndex = indexOfSearchQuery(displayName);
        if (startIndex == -1) {
            holder.name_analysis.setText(displayName);
        } else {
            SpannableString highlightedName = new SpannableString(displayName);
            highlightedName.setSpan(new TextAppearanceSpan(context, R.style.searchTextHiglight), startIndex,
                    startIndex + searchTerm.length(), 0);
            holder.name_analysis.setText(highlightedName);
        }
    }

    @Override
    public int getItemCount() {
        return analyses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView name_analysis, time_analysis,price_analysis;

        public ViewHolder(View itemView) {
            super(itemView);
            name_analysis = itemView.findViewById(R.id.name_analysis);
            time_analysis = itemView.findViewById(R.id.time_analysis);
            price_analysis = itemView.findViewById(R.id.price_analysis);
        }
    }

    private int indexOfSearchQuery(String displayName) {
        if (!TextUtils.isEmpty(searchTerm)) {
            return displayName.toLowerCase(Locale.getDefault()).indexOf(
                    searchTerm.toLowerCase(Locale.getDefault()));
        }
        return -1;
    }
}
