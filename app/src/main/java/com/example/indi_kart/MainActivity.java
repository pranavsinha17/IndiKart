package com.example.indi_kart;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.indi_kart.Model.Users;
import com.example.indi_kart.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
Button login_button,signup_button;
ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button=findViewById(R.id.login_btn);
        signup_button=findViewById(R.id.signup_btn);
        Paper.init(this);
        loading=new ProgressDialog(this);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(it);
            }
        });
        String UserPhone=Paper.book().read(Prevalent.UserPhoneKey);
        String UserPassword=Paper.book().read(Prevalent.UserPasswordKey);
        if(UserPhone!="" && UserPassword!="")
        {
            if(!TextUtils.isEmpty(UserPhone)&& !TextUtils.isEmpty(UserPassword))
            {
                ValidateCredentials(UserPhone,UserPassword);

                loading.setTitle("Already Logged In");
                loading.setMessage("Please Wait...");
                loading.setCanceledOnTouchOutside(false);
                loading.show();
            }

        }
    }

    private void ValidateCredentials(final String phone, final String password) {
        final DatabaseReference root;
        root= FirebaseDatabase.getInstance().getReference();
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(phone).exists())
                {
                    Users user=dataSnapshot.child("Users").child(phone).getValue(Users.class);
                    if(user.getPhone().equals(phone))
                    {
                        if(user.getPassword().equals(password))
                        {
                            Toast.makeText(MainActivity.this,"Logged In successfully",Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                            Intent it = new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(it);
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Wrong credentials..Please Create Account",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

