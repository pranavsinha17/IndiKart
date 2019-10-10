package com.example.indi_kart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.indi_kart.Model.Products;
import com.example.indi_kart.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class FoodDetail extends AppCompatActivity {
 FloatingActionButton addtoCart;
 ImageView productimage;
 ElegantNumberButton button;
 String saveCurrentDate,saveCurrentTime;
 TextView product_name,product_description,product_price;
 String productid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        addtoCart=findViewById(R.id.floating);
        productimage=findViewById(R.id.img);
        button=findViewById(R.id.numberbutton);
        product_name=findViewById(R.id.product_name);
        product_description=findViewById(R.id.product_description);
        product_price=findViewById(R.id.product_price);
productid= getIntent().getStringExtra("pid");
productdetail(productid);
addtoCart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        addToCartFun();
    }
});
    }

    private void addToCartFun() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("CartItem");
        final HashMap<String,Object> cartMap=new HashMap<>();
        cartMap.put("pid",productid);
        cartMap.put("pname",product_name.getText().toString());
        cartMap.put("price",product_price.getText().toString());
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurrentTime);
        cartMap.put("quantity",button.getNumber());
        cartMap.put("discount","");
        databaseReference.child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(productid)
                .updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful())
               {
                   databaseReference.child("Admin View").child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(productid)
                           .updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           Toast.makeText(FoodDetail.this,"Product Added to cart",Toast.LENGTH_SHORT).show();
                           Intent it=new Intent(FoodDetail.this,MainActivity2.class);
                           startActivity(it);
                       }
                   });
               }
            }
        });
        



    }

    private void productdetail(String productid) {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Products");
        databaseReference.child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Products products=dataSnapshot.getValue(Products.class);
                    product_name.setText(products.getPname());
                    product_description.setText(products.getDescription());
                    product_price.setText(products.getPrice());
                    Picasso.get().load(products.getImage()).into(productimage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
