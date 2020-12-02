package com.example.clinicFinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class InfantsPostAdapter extends FirebaseRecyclerAdapter<gastricpost, InfantsPostAdapter.PostViewHolder> {


    public InfantsPostAdapter(@NonNull FirebaseRecyclerOptions<gastricpost> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull InfantsPostAdapter.PostViewHolder holder, int position, @NonNull gastricpost model) {
        holder.title1.setText(model.getDoctorname());
        holder.title2.setText(model.getSelectedSpeciality());
        holder.title3.setText(model.getSelectedExperience());
        Picasso.get().load(model.getImgurl()).into(holder.imageView);
    }

    @NonNull
    @Override
    public InfantsPostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);

        return new InfantsPostAdapter.PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView title1,title2,title3;
        ImageView imageView;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title1=itemView.findViewById(R.id.title1);
            title2=itemView.findViewById(R.id.title2);
            title3=itemView.findViewById(R.id.title3);
            imageView=itemView.findViewById(R.id.image1);

        }
    }
}
