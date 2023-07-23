package com.example.itsmiddleme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.itsmiddleme.R;
import com.example.itsmiddleme.adapter.AllProdAdapter;
import com.example.itsmiddleme.model.AllProdModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllProduct extends AppCompatActivity {

    RecyclerView recyclerView;
    AllProdAdapter allProdAdapter;
    List<AllProdModel> allProdModelList;
    Toolbar toolbar;


    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);
        String type = getIntent().getStringExtra("type");

        toolbar = findViewById(R.id.allprod_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        allProdModelList =  new ArrayList<>();
        allProdAdapter = new AllProdAdapter(this, allProdModelList);
        recyclerView.setAdapter(allProdAdapter);


        if (type == null || type.isEmpty()){
            firestore.collection("ShowProduct").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            AllProdModel allProdModel = doc.toObject(AllProdModel.class);
                            allProdModelList.add(allProdModel);
                            allProdAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("mobile")){
            firestore.collection("ShowProduct").whereEqualTo("type","mobile").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            AllProdModel allProdModel = doc.toObject(AllProdModel.class);
                            allProdModelList.add(allProdModel);
                            allProdAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("pc")){
            firestore.collection("ShowProduct").whereEqualTo("type","pc").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            AllProdModel allProdModel = doc.toObject(AllProdModel.class);
                            allProdModelList.add(allProdModel);
                            allProdAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("console")){
            firestore.collection("ShowProduct").whereEqualTo("type","console").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            AllProdModel allProdModel = doc.toObject(AllProdModel.class);
                            allProdModelList.add(allProdModel);
                            allProdAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("voucher")){
            firestore.collection("ShowProduct").whereEqualTo("type","voucher").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            AllProdModel allProdModel = doc.toObject(AllProdModel.class);
                            allProdModelList.add(allProdModel);
                            allProdAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("streaming")){
            firestore.collection("ShowProduct").whereEqualTo("type","streaming").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            AllProdModel allProdModel = doc.toObject(AllProdModel.class);
                            allProdModelList.add(allProdModel);
                            allProdAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

    }
}