package com.example.clinicFinder;

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

public class ReportsPostAdapter extends FirebaseRecyclerAdapter<reportspost, ReportsPostAdapter.PostViewHolder> {

String namereport;
    public ReportsPostAdapter(@NonNull FirebaseRecyclerOptions<reportspost> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull reportspost model) {
        Picasso.get().load(model.getImgurl()).fit().centerCrop().into(holder.reportimg);
        holder.reporttitle.setText(model.getNamereport().toUpperCase()+" Report");
        namereport=model.getNamereport();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reports_item_layout, parent, false);

        return new ReportsPostAdapter.PostViewHolder(view);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView reportimg;
        TextView reporttitle,reportsdelete;
        String username,str;
        FirebaseAuth mAuth;
        public PostViewHolder(@NonNull final View itemView) {
            super(itemView);
            reportimg=itemView.findViewById(R.id.reportimg);
            reporttitle=itemView.findViewById(R.id.reporttitle);
            reportsdelete=itemView.findViewById(R.id.reportsdelete);
            mAuth= FirebaseAuth.getInstance();
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
            reportsdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("reports/"+username);
                    Query query = databaseReference.child(namereport);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                            }

                            Toast.makeText(itemView.getContext(), "Report Deleted Successfully", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            });
        }
    }
}
