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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminRegister extends AppCompatActivity {

    EditText email, name, password, number, code;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        number = findViewById(R.id.number);
        login =findViewById(R.id.goLogin);
        code = findViewById(R.id.adminCode);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminRegister.this, MainActivity.class));
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
                String textCode = code.getText().toString();

                if (TextUtils.isEmpty(textEmail)){
                    Toast.makeText(AdminRegister.this, "Please Enter an Email", Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty((textName))){
                    Toast.makeText(AdminRegister.this, "Please Enter a Name", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(textPassword)){
                    Toast.makeText(AdminRegister.this, "Please Enter a Password", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(textNumber)){
                    Toast.makeText(AdminRegister.this, "Please Enter a Number", Toast.LENGTH_LONG).show();
                }else if(textNumber.length()!= 10){
                    Toast.makeText(AdminRegister.this, "Number must be 10 digits long", Toast.LENGTH_LONG).show();
                }else if(textPassword.length()<6){
                    Toast.makeText(AdminRegister.this, "Password must contain at least 6 digits", Toast.LENGTH_LONG).show();
                } else if(!textCode.equals("1234567890")){
                    Toast.makeText(AdminRegister.this, "Your Admin Code is Not Recognised", Toast.LENGTH_LONG).show();
                }else {

                    registerUser(textEmail,textName,textPassword,textNumber,textCode);
                }

            }
        });
    }

    private void registerUser(String textEmail, String textName, String textPassword,String textNumber, String textCode){

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail,textPassword).addOnCompleteListener(AdminRegister.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    AdminUser user = new AdminUser(textEmail,textName,textPassword,textNumber,"admin");
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                    userRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AdminRegister.this, "User Registered", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(AdminRegister.this, AdminWelcome.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(AdminRegister.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }
    }