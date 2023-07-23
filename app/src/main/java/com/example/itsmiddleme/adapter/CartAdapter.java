package com.example.itsmiddleme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itsmiddleme.R;
import com.example.itsmiddleme.model.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<CartModel> list;

    int totalitem = 0;

    public CartAdapter(Context context, List<CartModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

        holder.date.setText(list.get(position).getCurrent_date());
        holder.time.setText(list.get(position).getCurrent_time());
        holder.price.setText(list.get(position).getPrice_product()+"Rp. ");
        holder.name.setText(list.get(position).getName_product());
        holder.totalprice.setText(String.valueOf(list.get(position).getTotal_price()));
        holder.totalqty.setText(list.get(position).getTotal_quantity());

        totalitem = totalitem + list.get(position).getTotal_price();
        Intent intent = new Intent("CalculatedTotal");
        intent.putExtra("calc_total",totalitem);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,date,time,totalqty,totalprice;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price_product);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            totalprice = itemView.findViewById(R.id.total_price);
            totalqty = itemView.findViewById(R.id.total_quantity);
        }
    }
}
