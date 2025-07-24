package com.spark.fees;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeesAdapter extends RecyclerView.Adapter<FeesAdapter.FeeViewHolder> {

    List<FeesModel> feeList;

    public FeesAdapter(List<FeesModel> feeList) {
        this.feeList = feeList;
    }

    @NonNull
    @Override
    public FeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fees_item, parent, false);
        return new FeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeeViewHolder holder, int position) {
        FeesModel fee = feeList.get(position);
        holder.feeName.setText(fee.getName());
        holder.feeAmount.setText("₹" + fee.getAmount());
    }

    @Override
    public int getItemCount() {
        return feeList.size();
    }

    static class FeeViewHolder extends RecyclerView.ViewHolder {
        TextView feeName, feeAmount;

        public FeeViewHolder(@NonNull View itemView) {
            super(itemView);
            feeName = itemView.findViewById(R.id.feeName);
            feeAmount = itemView.findViewById(R.id.feeAmount);
        }
    }
}
