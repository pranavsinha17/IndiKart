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
import android.widget.TextView;
import android.widget.Toast;

import com.example.indi_kart.Model.Users;
import com.example.indi_kart.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    Button Loggedin;
    EditText et1,et2;
    ProgressDialog loading;
    CheckBox rememberme;
    TextView admin,not_admin;
    String parentdb="Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Loggedin=findViewById(R.id.log);
        et1=findViewById(R.id.phone_number);
        et2=findViewById(R.id.password);
        rememberme=findViewById(R.id.rem_chk);
        admin=findViewById(R.id.admin_login);
        not_admin=findViewById(R.id.not_admin_login);
        loading=new ProgressDialog(this);
        Paper.init(this);
        Loggedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginAccess();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loggedin.setText("Admin Login");
                admin.setVisibility(View.INVISIBLE);
                not_admin.setVisibility(View.VISIBLE);
                parentdb="Admins";
            }
        });
        not_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loggedin.setText("Login");
                admin.setVisibility(View.VISIBLE);
                not_admin.setVisibility(View.INVISIBLE);
                parentdb="Users";
            }
        });
    }

    private void LoginAccess() {
        String phone=et1.getText().toString();
        String password=et2.getText().toString();
        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(LoginActivity.this,"Enter the name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(LoginActivity.this,"Enter the name",Toast.LENGTH_SHORT).show();
        }
        else{
            loading.setTitle("Log In");
            loading.setMessage("Please Wait while we are checking the credentials");
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            validateCredentials(phone,password);
        }
    }

    private void validateCredentials(final String phone, final String password) {
        if(rememberme.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }
        final DatabaseReference root;
        root= FirebaseDatabase.getInstance().getReference();
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentdb).child(phone).exists())
                {
                    Users user=dataSnapshot.child(parentdb).child(phone).getValue(Users.class);
                    if(user.getPhone().equals(phone))
                    {
                        if(user.getPassword().equals(password))
                        {
                            if(parentdb.equals("Admins")){
                                Toast.makeText(LoginActivity.this,"Logged In successfully",Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                                Intent it = new Intent(LoginActivity.this, CatagoryActivity.class);
                                startActivity(it);
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Logged In successfully",Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                                Prevalent.currentOnlineUser=user;
                                Intent it = new Intent(LoginActivity.this, MainActivity2.class);
                                startActivity(it);
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this,"Wrong credentials..Please Create Account",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

