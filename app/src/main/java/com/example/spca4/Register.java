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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText email, name, password, number;
    TextView login, admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        number = findViewById(R.id.number);
        login =findViewById(R.id.goLogin);
        admin =findViewById(R.id.admin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, AdminRegister.class));
            }
        });

        Button register = findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textEmail = email.getText().toString();
                String textName = name.getText().toString();
                String textPassword = password.getText().toString();
                String textNumber = number.getText().toString();

                if (TextUtils.isEmpty(textEmail)){
                    Toast.makeText(Register.this, "Please Enter an Email", Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty((textName))){
                    Toast.makeText(Register.this, "Please Enter a Name", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(textPassword)){
                    Toast.makeText(Register.this, "Please Enter a Password", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(textNumber)){
                    Toast.makeText(Register.this, "Please Enter a Number", Toast.LENGTH_LONG).show();
                }else if(textNumber.length()!= 10){
                    Toast.makeText(Register.this, "Number must be 10 digits long", Toast.LENGTH_LONG).show();
                }else if(textPassword.length()<6){
                    Toast.makeText(Register.this, "Password must contain at least 6 digits", Toast.LENGTH_LONG).show();
                } else {

                    registerUser(textEmail,textName,textPassword,textNumber);
                }

            }
        });
    }

    private void registerUser(String textEmail, String textName, String textPassword,String textNumber){

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail,textPassword).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    Users user = new Users(textEmail,textName,textPassword,textNumber);
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                    userRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "User Registered", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this, Welcome.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }
}