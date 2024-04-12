package com.example.spca4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdminDiscount extends AppCompatActivity {
    EditText inputCode;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_discount);
        inputCode = findViewById(R.id.inputCode);
        save = findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textCode = inputCode.getText().toString();

                if(TextUtils.isEmpty(textCode)){
                    Toast.makeText(AdminDiscount.this, "Please Enter a Code", Toast.LENGTH_LONG).show();
                }else{
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                    DatabaseReference codeRef = userRef.child("Codes");
                    String codeID = codeRef.push().getKey();
                    Map<String, Object> codeData = new HashMap<>();
                    codeData.put("code",textCode);

                    codeRef.child(codeID).setValue(codeData);
                        Toast.makeText(AdminDiscount.this, "Code Added", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}