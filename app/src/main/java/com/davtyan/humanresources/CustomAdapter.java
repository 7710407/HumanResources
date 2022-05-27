package com.davtyan.humanresources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList ids;
    private ArrayList names;
    private ArrayList divisions;
    private ArrayList salaries;

    CustomAdapter(Context context,
                  ArrayList ids,
                  ArrayList names,
                  ArrayList divisions,
                  ArrayList salaries) {
        this.context = context;
        this.ids = ids;
        this.names = names;
        this.divisions = divisions;
        this.salaries = salaries;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvId.setText(String.valueOf(ids.get(position)));
        holder.tvName.setText(String.valueOf(names.get(position)));
        holder.tvDivision.setText(String.valueOf(divisions.get(position)));
        holder.tvSalary.setText(String.valueOf(salaries.get(position)));
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvName;
        TextView tvDivision;
        TextView tvSalary;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvDivision = itemView.findViewById(R.id.tvDivision);
            tvSalary = itemView.findViewById(R.id.tvSalary);
        }
    }
}
