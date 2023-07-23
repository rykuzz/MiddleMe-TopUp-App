package com.example.itsmiddleme.adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.itsmiddleme.R;
import com.example.itsmiddleme.activity.DetailProduct;
import com.example.itsmiddleme.model.AllProdModel;

import java.util.List;

public class AllProdAdapter extends RecyclerView.Adapter<AllProdAdapter.ViewHolder> {

    private Context context;
    private List<AllProdModel> list;

    public AllProdAdapter(Context context, List<AllProdModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AllProdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllProdAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.itemimage);
        holder.cost.setText("Rp."+(list.get(position).getPrice()));
        holder.name.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailProduct.class);
                intent.putExtra("detail",list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemimage;
        private TextView cost,name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemimage = itemView.findViewById(R.id.item_image);
            cost = itemView.findViewById(R.id.item_cost);
            name = itemView.findViewById(R.id.item_nam);
        }
    }
}
