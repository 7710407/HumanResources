package com.davtyan.humanresources.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.davtyan.humanresources.provider.Employee;
import com.davtyan.humanresources.R;

import java.util.ArrayList;
import static com.davtyan.humanresources.ConstValues.*;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList<Employee> employees;

    Animation animation;

    public CustomAdapter(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public void setEmpoyees(ArrayList<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
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
        Employee employee = employees.get(position);
        holder.tvId.setText(employee.getId());
        holder.tvName.setText(employee.getName());
        holder.tvDivision.setText(employee.getDivision());
        holder.tvSalary.setText(employee.getSalary());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra(ID, String.valueOf(employee.getId()));
                intent.putExtra(NAME, String.valueOf(employee.getName()));
                intent.putExtra(DIVISION, String.valueOf(employee.getDivision()));
                intent.putExtra(SALARY, String.valueOf(employee.getSalary()));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees == null ? 0 : employees.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvName;
        TextView tvDivision;
        TextView tvSalary;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvDivision = itemView.findViewById(R.id.tvDivision);
            tvSalary = itemView.findViewById(R.id.tvSalary);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            animation = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(animation);
        }
    }
}
