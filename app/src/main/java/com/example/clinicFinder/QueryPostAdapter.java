package com.example.clinicFinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class QueryPostAdapter extends FirebaseRecyclerAdapter<querypost, QueryPostAdapter.PostViewHolder> {

String deletequery;
    public QueryPostAdapter(@NonNull FirebaseRecyclerOptions<querypost> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull querypost model) {
       holder.topic.setText("Query: "+model.getQtopic().toUpperCase());
       holder.problem.setText("Related To: "+model.getQproblem().toUpperCase());
       holder.textView57.setText("Description: "+model.getQdescribe().toUpperCase());
       deletequery=model.getQtopic();
       if(model.getQanswer()!=null) {
           holder.answers.setText("Answer: " + model.getQanswer());
       }else{
           holder.answers.setText("Answer: " + "No Answers Yet");
       }

    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.query_item_layout, parent, false);

        return new QueryPostAdapter.PostViewHolder(view);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView topic,problem,answers,textView56,textView57;
        FirebaseAuth mAuth;
        public PostViewHolder(@NonNull final View itemView) {
            super(itemView);
            topic=itemView.findViewById(R.id.textView49);
            problem=itemView.findViewById(R.id.textView50);
            answers=itemView.findViewById(R.id.textView52);
            textView56=itemView.findViewById(R.id.textView56);
            textView57=itemView.findViewById(R.id.textView57);
            textView56.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth=FirebaseAuth.getInstance();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("querys/"+"khannadeema");
                    Query query = databaseReference.child(deletequery);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().removeValue();
                            Toast.makeText(itemView.getContext(), "Query Deleted Successfully", Toast.LENGTH_LONG).show();
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
