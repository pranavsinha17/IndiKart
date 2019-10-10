package com.example.indi_kart.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.indi_kart.ItemClickListener;
import com.example.indi_kart.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView productname,productprice,productquantity;
    ItemClickListener itemClickListener;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        productname=itemView.findViewById(R.id.productname);
        productprice=itemView.findViewById(R.id.productprice);
        productquantity=itemView.findViewById(R.id.productquantity);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }
}
