package com.example.clinicFinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class GastricPostAdapter extends FirebaseRecyclerAdapter<gastricpost, GastricPostAdapter.PostViewHolder> {

    public GastricPostAdapter(@NonNull FirebaseRecyclerOptions<gastricpost> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull gastricpost model) {
        holder.title1.setText(model.getDoctorname());
        holder.title2.setText(model.getSelectedSpeciality());
        holder.title3.setText(model.getSelectedExperience());
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);

        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView title1,title2,title3;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title1=itemView.findViewById(R.id.title1);
            title2=itemView.findViewById(R.id.title2);
            title3=itemView.findViewById(R.id.title3);

        }
    }
}
