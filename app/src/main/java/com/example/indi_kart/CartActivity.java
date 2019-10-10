package com.example.indi_kart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.indi_kart.Model.Cart;
import com.example.indi_kart.Prevalent.Prevalent;
import com.example.indi_kart.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends AppCompatActivity {
RecyclerView recyclerView;
RecyclerView.LayoutManager layoutManager;
Button next;
public TextView total;
public int fullTotal=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        next=findViewById(R.id.next);
        total=findViewById(R.id.total);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CartActivity.this,FinalOrder.class);
                startActivity(it);
            }
        });
    }
    protected void onStart() {

        super.onStart();
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("CartItem");
        FirebaseRecyclerOptions<Cart>options=new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(databaseReference.child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products"),Cart.class).build();
        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter=new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull Cart cart) {
          cartViewHolder.productname.setText("Product Name = "+cart.getPname());
                cartViewHolder.productquantity.setText("Quantity = "+cart.getQuantity());
                cartViewHolder.productprice.setText("Price = "+cart.getPrice());
                int totalPrice=(Integer.valueOf(cart.getPrice()))*(Integer.valueOf(cart.getQuantity()));
                fullTotal+=totalPrice;
                total.setText("Total Price= Rs."+fullTotal);

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
                CartViewHolder holder=new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}
