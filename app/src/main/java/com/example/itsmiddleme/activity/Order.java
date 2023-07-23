package com.example.itsmiddleme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.itsmiddleme.R;
import com.example.itsmiddleme.adapter.HotProductAdapter;
import com.example.itsmiddleme.adapter.OrderAdapter;
import com.example.itsmiddleme.model.AllProdModel;
import com.example.itsmiddleme.model.CartModel;
import com.example.itsmiddleme.model.HotProductModels;
import com.example.itsmiddleme.model.OrderModel;
import com.example.itsmiddleme.model.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Order extends AppCompatActivity implements OrderAdapter.Selectoption {

    Button addorder;
    RecyclerView recyclerView;
    private List<OrderModel> orderModelList;
    private OrderAdapter orderAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Button addorderbtn,checkoutbtn;
    Toolbar toolbar;
    String addresspointer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        toolbar = findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Object obj = getIntent().getSerializableExtra("item");

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.address_recycler);
        checkoutbtn = findViewById(R.id.payment_btn);
        addorderbtn = findViewById(R.id.add_address_btn);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        orderModelList = new ArrayList<>();
        orderAdapter = new OrderAdapter(getApplicationContext(),orderModelList,this);
        recyclerView.setAdapter(orderAdapter);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot doc : task.getResult().getDocuments()){
                        OrderModel orderModel = doc.toObject(OrderModel.class);
                        orderModelList.add(orderModel);
                        orderAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double amount = 0.0;
               if(obj instanceof HotProductModels){
                    HotProductModels hotProductModels = (HotProductModels) obj;
                    amount = hotProductModels.getPrice();
               }if(obj instanceof PopularModel){
                    PopularModel popularModel = (PopularModel) obj;
                    amount = popularModel.getPrice();
                }if(obj instanceof AllProdModel){
                    AllProdModel allProdModel = (AllProdModel) obj;
                    amount = allProdModel.getPrice();
                }
               Intent intent = new Intent(Order.this,Checkout.class);
               intent.putExtra("amount",amount);
               startActivity(intent);
            }
        });

        addorder = findViewById(R.id.add_address_btn);
        addorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(Order.this,AddOrder.class));
            }
        });
    }

    @Override
    public void setoption(String adress) {
        addresspointer = adress;
    }
}