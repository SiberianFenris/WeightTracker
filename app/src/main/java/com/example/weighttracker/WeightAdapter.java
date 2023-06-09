package com.example.weighttracker;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.WeightViewHolder> {
    private List<WeightEntry> dailyWeights;
    private OnItemClickListener listener;
    private WeightEntry entry;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.listener = listener;
    }

    public WeightAdapter(List<WeightEntry> dailyWeights) {

        this.dailyWeights = dailyWeights;
    }

    @NonNull
    @Override
    public WeightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_item, parent, false);
        return new WeightViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull WeightViewHolder holder, int position) {
        WeightEntry dailyWeight = dailyWeights.get(position);
        holder.bind(dailyWeight);
    }

    @Override
    public int getItemCount() {
        return dailyWeights.size();
    }

    public static class WeightViewHolder extends RecyclerView.ViewHolder {
        EditText dateTextView;
        EditText weightTextView;
        Button editButton;
        Button deleteButton;
        private WeightEntry entry;

        public WeightViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.weight_date);
            weightTextView = itemView.findViewById(R.id.weight_value);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);

            // Set the typeface of the TextView objects to bold
            dateTextView.setTypeface(Typeface.DEFAULT_BOLD);
            weightTextView.setTypeface(Typeface.DEFAULT_BOLD);

            // Handle click events for the edit and delete buttons
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String date = dateTextView.getText().toString();
                    String weight = weightTextView.getText().toString();
                    WeightDB.getInstance().updateDailyWeight(date, weight);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String date = dateTextView.getText().toString();
                    String weight = weightTextView.getText().toString();
                    WeightDB.getInstance().deleteDailyWeight(date, weight);
                    //TODO: removing current holder
                }
            });
        }
        public void bind(WeightEntry entry){
            this.entry = entry;
            dateTextView.setText(entry.getDate());
            weightTextView.setText(entry.getWeight());
        }
    }
}