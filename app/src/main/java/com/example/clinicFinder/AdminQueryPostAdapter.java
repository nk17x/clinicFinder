package com.example.clinicFinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class AdminQueryPostAdapter extends FirebaseRecyclerAdapter<querypost, AdminQueryPostAdapter.PostViewHolder> {
String descriptiona,usernamea,querya,realateda,answera;
    public AdminQueryPostAdapter(@NonNull FirebaseRecyclerOptions<querypost> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull querypost model) {
        holder.tdescription.setText("Description: "+model.getQdescribe().toUpperCase());
        holder.tusername.setText("Username: "+model.getUsername().toUpperCase());
        holder.topic.setText("Query: "+model.getQtopic().toUpperCase());
        holder.problem.setText("Related To: "+model.getQproblem().toUpperCase());
        descriptiona=model.getQdescribe();
        usernamea=model.getUsername();
        querya=model.getQtopic();
        realateda=model.getQproblem();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adminquery_item_layout, parent, false);

        return new AdminQueryPostAdapter.PostViewHolder(view);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView topic,problem,tusername,tdescription;
        Button submitt;
        EditText qanswer;
        FirebaseAuth mAuth;
        DatabaseReference databaseReference;
        FirebaseDatabase rootNode;
        AdminQueryHelperClass helperClass;

        public PostViewHolder(@NonNull final View itemView) {
            super(itemView);
            mAuth=FirebaseAuth.getInstance();
            tusername=itemView.findViewById(R.id.textView51);
            topic=itemView.findViewById(R.id.textView53);
            problem=itemView.findViewById(R.id.textView54);
            tdescription=itemView.findViewById(R.id.textView55);
           qanswer=itemView.findViewById(R.id.qanswer2);
            submitt=itemView.findViewById(R.id.button20);
            submitt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answera=qanswer.getText().toString();
                    databaseReference=FirebaseDatabase.getInstance().getReference("querys/"+"khannadeema");
                    helperClass = new AdminQueryHelperClass(usernamea,querya,realateda,descriptiona,answera);
                    databaseReference.child(String.valueOf(querya)).setValue(helperClass);

                    Toast.makeText(itemView.getContext(), "Query Posted Successful", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
