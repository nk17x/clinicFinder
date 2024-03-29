package com.example.clinicFinder;

import android.content.Context;
import android.content.Intent;
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

public class PregnancyPostAdapter extends FirebaseRecyclerAdapter<gastricpost, PregnancyPostAdapter.PostViewHolder> {

Context context;
    public PregnancyPostAdapter(@NonNull FirebaseRecyclerOptions<gastricpost> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PregnancyPostAdapter.PostViewHolder holder, int position, @NonNull gastricpost model) {
        holder.title1.setText("Dr."+model.getDoctorname().toUpperCase());
        holder.title2.setText(model.getSelectedSpeciality());
        holder.title3.setText(model.getSelectedExperience());
        Picasso.get().load(model.getImgurl()).into(holder.imageView);
    }

    @NonNull
    @Override
    public PregnancyPostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);

        return new PregnancyPostAdapter.PostViewHolder(view);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();
                    String selected =getRef(itemPosition).getKey();
                    Intent intent=new Intent(context,bookAppointment.class);
                    intent.putExtra("selectedDoctor",selected);
                    intent.putExtra("speciality","doctors/Gynaecologist");
                    context.startActivity(intent);

                }
            });

        }
    }
}
