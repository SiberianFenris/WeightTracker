package com.example.weighttracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.WeightViewHolder> {
    private List<WeightEntry> dailyWeights;

    public WeightAdapter(List<WeightEntry> dailyWeights) {
        this.dailyWeights = dailyWeights;
    }

    @NonNull
    @Override
    public WeightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_item, parent, false);
        return new WeightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeightViewHolder holder, int position) {
        WeightEntry dailyWeight = dailyWeights.get(position);
        holder.dateTextView.setText(dailyWeight.getDate());
        holder.weightTextView.setText(String.valueOf(dailyWeight.getWeight()));
    }

    @Override
    public int getItemCount() {
        return dailyWeights.size();
    }

    public static class WeightViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView weightTextView;

        public WeightViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.weight_date);
            weightTextView = itemView.findViewById(R.id.weight_value);
        }
    }
}