package com.example.volley_post_demo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleHolder> {
    MainActivity2 mainActivity2;
    List<Product_Data> productdatalist;

    public RecyclerAdapter(MainActivity2 mainActivity2, List<Product_Data> productdatalist) {
        this.mainActivity2 = mainActivity2;
        this.productdatalist = productdatalist;
        Log.d("TTT", "onResponse: productDataList on Adapter="+productdatalist.toString());
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity2).inflate(R.layout.main2_item,parent,false);
        RecycleHolder holder = new RecycleHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecycleHolder holder, int position) {
        holder.txt1.setText(""+productdatalist.get(position).getId());
        holder.txt2.setText(""+productdatalist.get(position).getUid());
        holder.txt3.setText(""+productdatalist.get(position).getProName());
        holder.txt4.setText(""+productdatalist.get(position).getProDes());
        holder.txt5.setText(""+productdatalist.get(position).getProPrice());
        Glide
                .with(mainActivity2)
                .load(productdatalist.get(position).proImage)
                .centerCrop()
                .placeholder(R.drawable.animation)
                .into(holder.img);

        //holder.img.setImageURI(Uri.parse(""+dataModalList.get(position).getUrl()));
    }

    @Override
    public int getItemCount() {
        return productdatalist.size();
    }


    public class RecycleHolder extends RecyclerView.ViewHolder {
        TextView txt1,txt2,txt3,txt4,txt5;
        ImageView img;
        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.item_id);
            txt2=itemView.findViewById(R.id.item_uid);
            txt3=itemView.findViewById(R.id.item_pro_name);
            txt4=itemView.findViewById(R.id.item_pro_des);
            txt5=itemView.findViewById(R.id.item_pro_price);
            img=itemView.findViewById(R.id.item_pro_images);

        }
    }
}


