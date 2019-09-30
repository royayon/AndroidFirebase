package com.ayon.androidfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.MyViewHolder> {

    Context context;
    ArrayList<Info> infos;

    public FirebaseAdapter(Context c, ArrayList<Info> i) {
        context = c;
        infos = i;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText("Name: " + infos.get(position).getName());
        holder.age.setText("Age: " + infos.get(position).getAge());
        holder.desc.setText("Description: " + infos.get(position).getDesc());
        holder.serial.setText("Serial: " + String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,age,desc,serial;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            desc = itemView.findViewById(R.id.desc);
            serial = itemView.findViewById(R.id.serial);



        }
    }
}
