package com.example.itsmiddleme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itsmiddleme.R;
import com.example.itsmiddleme.model.OrderModel;

import org.w3c.dom.Text;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    List<OrderModel> orderModelList;
    Selectoption selectoption;
    private RadioButton selectradio;

    public OrderAdapter(Context context, List<OrderModel> orderModelList, Selectoption selectoption) {
        this.context = context;
        this.orderModelList = orderModelList;
        this.selectoption = selectoption;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {

        holder.address.setText(orderModelList.get(position).getOrderpoint());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(OrderModel address:orderModelList){
                    address.setOption(false);
                }
                orderModelList.get(position).setOption(true);
                if(selectradio!=null){
                    selectradio.setChecked(false);
                }
                selectradio = (RadioButton) view;
                selectradio.setChecked(true);
                selectoption.setoption(orderModelList.get(position).getOrderpoint());
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.address_add);
            radioButton = itemView.findViewById(R.id.select_address);

        }
    }

    public interface Selectoption{
        void setoption(String adress);
    }

}
