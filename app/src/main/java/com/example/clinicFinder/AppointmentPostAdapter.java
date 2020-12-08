package com.example.clinicFinder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AppointmentPostAdapter extends FirebaseRecyclerAdapter<appointmentpost, AppointmentPostAdapter.PostViewHolder> {
    String docname,str,username;

    public AppointmentPostAdapter(@NonNull FirebaseRecyclerOptions<appointmentpost> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull appointmentpost model) {
        Picasso.get().load(model.getImgurl()).into(holder.aimage1);
        holder.atitle1.setText("Dr."+model.getDoctorname().toUpperCase());
        docname=model.getDoctorname();
        holder.atitle2.setText(model.getSpeciality());
        holder.atitle3.setText("Time : "+model.getDbtime());
        holder.atitle4.setText("Date : "+model.getDbdate());
    }

    @NonNull
    @Override
    public AppointmentPostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_item_layout, parent, false);

        return new AppointmentPostAdapter.PostViewHolder(view);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView atitle1,atitle2,atitle3,atitle4,atitle5;
        ImageView aimage1;
        FirebaseAuth mAuth;
        public PostViewHolder(@NonNull final View itemView) {
            super(itemView);
            atitle1=itemView.findViewById(R.id.atitle1);
            atitle2=itemView.findViewById(R.id.atitle2);
            atitle3=itemView.findViewById(R.id.atitle3);
            aimage1=itemView.findViewById(R.id.aimage1);
            atitle4=itemView.findViewById(R.id.atitle4);
            atitle5=itemView.findViewById(R.id.atitle5);
            mAuth=FirebaseAuth.getInstance();
            str= mAuth.getCurrentUser().getEmail();
            username="";
            if(str.indexOf(".")!=-1){
                String [] twoStringArray2= str.split("@",2);
                String username2= twoStringArray2[0];
                String [] twoStringArray= username2.split("\\.",2);
                username= twoStringArray[0];
            }else{
                String [] twoStringArray2= str.split("@",2);
                username= twoStringArray2[0];}
            atitle5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("appointments/"+username);
                        Query query = databaseReference.child(docname);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().removeValue();
                                }
                                Toast.makeText(itemView.getContext(), "Appointment Cancelled Successfully", Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    };

            });

        }
    }
}
