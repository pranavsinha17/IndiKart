package com.example.indi_kart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.indi_kart.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class FinalOrder extends AppCompatActivity {
EditText et1,et2,et3,et4,et5;
String a,b,c,d,e;
Button bt;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order);
        et1=findViewById(R.id.entername);
        et2=findViewById(R.id.phone);
        et3=findViewById(R.id.Address);
        et4=findViewById(R.id.city);
        et5=findViewById(R.id.pin);
        bt=findViewById(R.id.confirm_order);
        loading = new ProgressDialog(this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=et1.getText().toString();
                b=et2.getText().toString();
                c=et3.getText().toString();
                d=et4.getText().toString();
                e=et5.getText().toString();
                if (TextUtils.isEmpty(a)) {
                    Toast.makeText(FinalOrder.this, "Enter the name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(b)) {
                    Toast.makeText(FinalOrder.this, "Enter the number", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(c)) {
                    Toast.makeText(FinalOrder.this, "Enter the Address", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(d)) {
                    Toast.makeText(FinalOrder.this, "Enter the city", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(e)) {
                    Toast.makeText(FinalOrder.this, "Enter the ZIP code", Toast.LENGTH_SHORT).show();
                }
                else{
//                    loading.setTitle("Confirming Order");
//                    loading.setMessage("Please Wait...");
//                    loading.setCanceledOnTouchOutside(false);
//                    loading.show();
//                    Toast.makeText(FinalOrder.this, "Order Confirmed", Toast.LENGTH_SHORT).show();
//                    Intent it=new Intent(FinalOrder.this,Hurray.class);
//                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(it);
//                    finish();
                    confirm();
                }
            }
        });



    }

    private void confirm() {
         final String saveCurrentDate,saveCurrentTime;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getPhone());
        HashMap<String,Object> orderMap=new HashMap<>();
        orderMap.put("name",et1.getText().toString());
        orderMap.put("phone",et2.getText().toString());
        orderMap.put("address",et3.getText().toString());
        orderMap.put("city",et4.getText().toString());
        orderMap.put("date",saveCurrentDate);
        orderMap.put("time",saveCurrentTime);
        orderMap.put("pin",et5.getText().toString());
        orderMap.put("discount","");
        orderMap.put("state","not shipped");
        databaseReference.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                          FirebaseDatabase.getInstance().getReference().child("CartItem").child("User View").child(Prevalent.currentOnlineUser.getPhone())
                                  .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  if(task.isSuccessful())
                                  {
                                      Toast.makeText(FinalOrder.this, "Order Confirmed", Toast.LENGTH_SHORT).show();
              Intent it=new Intent(FinalOrder.this,Hurray.class);
              it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(it);
                    finish();
                                  }
                              }
                          });
                }
            }
        });

    }

}
