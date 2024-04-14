package com.example.spca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView createNew;
    Button loginB;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        createNew= findViewById(R.id.newAccount);
        loginB= findViewById(R.id.btnLogin);

        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();

                if(TextUtils.isEmpty(textEmail)){
                    Toast.makeText(MainActivity.this, "Please Enter an Email", Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty(textPassword)){
                    Toast.makeText(MainActivity.this, "Please Enter a Password", Toast.LENGTH_LONG).show();
                }else{
                    login(textEmail, textPassword);
                    //startActivity(new Intent(MainActivity.this, Welcome.class));
                }
            }
        });
    }

    private void login(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    // Retrieve user data from the database
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(auth.getCurrentUser().getUid());
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Users userData = dataSnapshot.getValue(Users.class);
                            String userType = dataSnapshot.child("code").getValue(String.class);
                            if (userType != null && userType.equals("admin")){
                                Intent intent = new Intent(MainActivity.this, AdminWelcome.class);
                                startActivity(intent);
                            }else {
                                // User is not admin, navigate to WelcomeActivity
                                Intent intent = new Intent(MainActivity.this, Welcome.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle the error
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}