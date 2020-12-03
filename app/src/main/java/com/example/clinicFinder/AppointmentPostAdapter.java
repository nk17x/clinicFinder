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

public class AppointmentPostAdapter extends FirebaseRecyclerAdapter<appointmentpost, AppointmentPostAdapter.PostViewHolder> {

    public AppointmentPostAdapter(@NonNull FirebaseRecyclerOptions<appointmentpost> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull appointmentpost model) {
        holder.atitle1.setText("Dr."+model.getDoctorname().toUpperCase());
        holder.atitle2.setText(model.getSpeciality());
        holder.atitle3.setText("Time :"+model.getDatetime());
        Picasso.get().load(model.getImgurl()).into(holder.aimage1);
    }

    @NonNull
    @Override
    public AppointmentPostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_item_layout, parent, false);

        return new AppointmentPostAdapter.PostViewHolder(view);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView atitle1,atitle2,atitle3;
        ImageView aimage1;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            atitle1=itemView.findViewById(R.id.atitle1);
            atitle2=itemView.findViewById(R.id.atitle2);
            atitle3=itemView.findViewById(R.id.atitle3);
            aimage1=itemView.findViewById(R.id.aimage1);
        }
    }
}
