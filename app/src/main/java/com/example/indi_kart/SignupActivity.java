package com.example.indi_kart;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity  {
    Button CreateAccount;
    EditText et1, et2, et3;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et1 = findViewById(R.id.name);
        et2 = findViewById(R.id.phone_number);
        et3 = findViewById(R.id.password);
        CreateAccount=findViewById(R.id.log);
        loading = new ProgressDialog(this);
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }

    private void createNewAccount() {
        String name = et1.getText().toString();
        String phone = et2.getText().toString();
        String password = et3.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(SignupActivity.this, "Enter the name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(SignupActivity.this, "Enter the name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(SignupActivity.this, "Enter the name", Toast.LENGTH_SHORT).show();
        } else {
            loading.setTitle("Sign Up");
            loading.setMessage("Please Wait while we are checking the credentials");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            validateCredentials(name, phone, password);
        }

    }

    private void validateCredentials(final String name, final String phone, final String password) {
        final DatabaseReference root;
        root = FirebaseDatabase.getInstance().getReference();
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("phone", phone);
                    map.put("password", password);
                    map.put("name", name);
                    root.child("Users").child(phone).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Congrats!!! Your account has been created", Toast.LENGTH_SHORT).show();
                                loading.dismiss();

                                Intent it = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(it);
                            } else {
                                loading.dismiss();
                                Toast.makeText(SignupActivity.this, "Connectivity Issues,Check Your Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignupActivity.this, "This Phone number is already used", Toast.LENGTH_SHORT).show();
                    loading.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }


}
