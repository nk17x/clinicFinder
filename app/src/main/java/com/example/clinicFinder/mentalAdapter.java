
package com.example.clinicFinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class mentalAdapter extends RecyclerView.Adapter<mentalAdapter.MentalViewHolder> {
    private String[] data,experience;
    public mentalAdapter(String[] data,String[] experience){
        this.data=data;
        this.experience=experience;
    }
    @NonNull
    @Override
    public MentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.list_item_layout,parent,false);
        return  new MentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentalViewHolder holder, int position) {
        String title1s=data[position];
        String title3s=experience[position];
        holder.title1.setText(title1s);
        holder.title2.setText("Neurologist");
        holder.title3.setText(title3s);
        holder.title4.setText("know more");
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MentalViewHolder extends RecyclerView.ViewHolder{
        ImageView image1;
        TextView title1,title2,title3,title4;
        public MentalViewHolder(@NonNull View itemView) {
            super(itemView);
           image1=(ImageView) itemView.findViewById(R.id.image1);
            title1=(TextView) itemView.findViewById(R.id.title1);
            title2=(TextView) itemView.findViewById(R.id.title2);
            title3=(TextView) itemView.findViewById(R.id.title3);
            title4=(TextView) itemView.findViewById(R.id.title4);
        }
    }
}
