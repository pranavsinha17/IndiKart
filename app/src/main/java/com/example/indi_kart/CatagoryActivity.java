package com.example.indi_kart;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CatagoryActivity extends AppCompatActivity {
ImageView shirt,shoe,female,books,purse,sweater,sports,glasses,laptops,mobiles,watches,headphones,hats,image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        shirt=findViewById(R.id.shirt);
        shoe=findViewById(R.id.shoe);
        female=findViewById(R.id.female);
        books=findViewById(R.id.books);
        purse=findViewById(R.id.purse);
        sweater=findViewById(R.id.sweater);
        sports=findViewById(R.id.sports);
        glasses=findViewById(R.id.glasses);
        laptops=findViewById(R.id.laptops);
        mobiles=findViewById(R.id.mobiles);
        watches=findViewById(R.id.watches);
        headphones=findViewById(R.id.headphones);
        hats=findViewById(R.id.hats);
        image=findViewById(R.id.image);
        shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","shirt");
                startActivity(it);
            }
        });
        shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","shoes");
                startActivity(it);
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","female Dress");
                startActivity(it);
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","books");
                startActivity(it);
            }
        });
        purse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","purse");
                startActivity(it);
            }
        });
        sweater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","sweator");
                startActivity(it);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","sports wear");
                startActivity(it);
            }
        });
        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","goggles");
                startActivity(it);
            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","laptops");
                startActivity(it);
            }
        });
        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","mobiles");
                startActivity(it);
            }
        });
        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","watches");
                startActivity(it);
            }
        });
        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","headphones");
                startActivity(it);
            }
        });
        hats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","hats");
                startActivity(it);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CatagoryActivity.this,AdminAddProduct.class);
                it.putExtra("catagory","dslr");
                startActivity(it);
            }
        });

    }
}
