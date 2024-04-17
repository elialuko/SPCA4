package com.example.spca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewCustomers extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Users> list;
    TwoAdapter adapter;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);
        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TwoAdapter(this, list);
        recyclerView.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("Users");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    boolean isAdmin = false;
                    DataSnapshot codesSnapshot = userSnapshot.child("Codes");
                    for (DataSnapshot codeSnapshot : codesSnapshot.getChildren()) {
                        String code = codeSnapshot.child("code").getValue(String.class);
                        if (code != null && code.equals("admin")) {
                            isAdmin = true;
                            break;
                        }
                    }
                    if (!isAdmin) {
                        Users user = userSnapshot.getValue(Users.class);
                        Log.d("FirebaseData", "User: " + user.toString());
                        list.add(user);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}