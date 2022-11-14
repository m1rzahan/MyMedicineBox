package com.mirzahansuslu.medicineapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private ArrayList medicineId, medicineName, medicineType, medicineCount;
    Activity activity;

    CustomAdapter(Activity activity,Context context, ArrayList medicineId,  ArrayList medicineName,  ArrayList medicineType ,  ArrayList medicineCount) {
        this.context = context;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.medicineCount = medicineCount;
        this.activity = activity;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
       View view =  layoutInflater.inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {

        holder.medicine_id_txt.setText(String.valueOf(medicineId.get(position)));
        holder.medicine_name_txt.setText(String.valueOf(medicineName.get(position)));
        holder.medicine_type_txt.setText(String.valueOf(medicineType.get(position)));
        holder.medicine_count_txt.setText(String.valueOf(medicineCount.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(medicineId.get(position)));
                intent.putExtra("name",String.valueOf(medicineName.get(position)));
                intent.putExtra("type",String.valueOf(medicineType.get(position)));
                intent.putExtra("count",String.valueOf(medicineCount.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });


    }


    @Override
    public int getItemCount() {
        return medicineId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView medicine_id_txt, medicine_name_txt , medicine_type_txt, medicine_count_txt;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicine_id_txt = itemView.findViewById(R.id.medicine_id_txt);
            medicine_name_txt = itemView.findViewById(R.id.medicine_name_txt);
            medicine_type_txt = itemView.findViewById(R.id.medicine_type_txt);
            medicine_count_txt = itemView.findViewById(R.id.medicine_count_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
