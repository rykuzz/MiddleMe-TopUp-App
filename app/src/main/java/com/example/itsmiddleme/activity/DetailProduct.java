package com.example.itsmiddleme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.itsmiddleme.R;
import com.example.itsmiddleme.model.AllProdModel;
import com.example.itsmiddleme.model.HotProductModels;
import com.example.itsmiddleme.model.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class DetailProduct extends AppCompatActivity {

    ImageView detail_img,add_item,remove_item;
    TextView rating,name,price,desc,qty;
    Button add_cart, checkout;
    Toolbar toolbar;
    int totalqty = 1;
    int totalprice = 0;

    HotProductModels hotProductModels = null;
    PopularModel popularModel = null;
    AllProdModel allProdModel = null;
    FirebaseAuth auth;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        final Object obj = getIntent().getSerializableExtra("detail");

        if (obj instanceof HotProductModels){
            hotProductModels = (HotProductModels) obj;
        }else if(obj instanceof PopularModel){
            popularModel = (PopularModel) obj;
        }else if(obj instanceof AllProdModel){
            allProdModel = (AllProdModel) obj;
        }

        detail_img = findViewById(R.id.detail_img);
        qty = findViewById(R.id.qty);
        add_item = findViewById(R.id.add_item);
        remove_item = findViewById(R.id.remove_item);
        rating = findViewById(R.id.prod_rating);
        name = findViewById(R.id.detail_name);
        price = findViewById(R.id.detail_price);
        desc = findViewById(R.id.detail_desc);
        add_cart = findViewById(R.id.add_cart);
        checkout = findViewById(R.id.checkout);

        if(hotProductModels != null){
            Glide.with(getApplicationContext()).load((hotProductModels.getImg_url())).into(detail_img);
            name.setText(hotProductModels.getName());
            rating.setText(hotProductModels.getRating());
            desc.setText(hotProductModels.getDesc());
            price.setText(String.valueOf(hotProductModels.getPrice()));
            name.setText(hotProductModels.getName());

            totalprice = hotProductModels.getPrice() * totalqty;

        }

        if(popularModel != null){
            Glide.with(getApplicationContext()).load((popularModel.getImg_url())).into(detail_img);
            name.setText(popularModel.getName());
            rating.setText(popularModel.getRating());
            desc.setText(popularModel.getDesc());
            price.setText(String.valueOf(popularModel.getPrice()));
            name.setText(popularModel.getName());

            totalprice = popularModel.getPrice() * totalqty;

        }

        if(allProdModel != null){
            Glide.with(getApplicationContext()).load((allProdModel.getImg_url())).into(detail_img);
            name.setText(allProdModel.getName());
            rating.setText(allProdModel.getRating());
            desc.setText(allProdModel.getDesc());
            price.setText(String.valueOf(allProdModel.getPrice()));
            name.setText(allProdModel.getName());

            totalprice = allProdModel.getPrice() * totalqty;

        }

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_cart();
            }
        });

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalqty<10){
                    totalqty++;
                    qty.setText(String.valueOf(totalqty));

                    if (hotProductModels != null){
                        totalprice = hotProductModels.getPrice() * totalqty;
                    }
                    if (popularModel != null){
                        totalprice = popularModel.getPrice() * totalqty;
                    }
                    if (allProdModel != null){
                        totalprice = allProdModel.getPrice() * totalqty;
                    }
                }

            }
        });

        remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalqty>1){
                    totalqty--;
                    qty.setText(String.valueOf(totalqty));
                }
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProduct.this,Order.class);
                if(hotProductModels != null){
                    intent.putExtra("item",hotProductModels);
                }
                if(popularModel != null){
                    intent.putExtra("item",popularModel);
                }
                if(allProdModel != null){
                    intent.putExtra("item",allProdModel);
                }
                startActivity(intent);
            }
        });

    }

    private void add_cart() {
        String save_time,save_date;
        Calendar calldate = Calendar.getInstance();

        SimpleDateFormat currentdate = new SimpleDateFormat("DD mm, yyyy");
        save_date = currentdate.format(calldate.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");
        save_time = currenttime.format(calldate.getTime());

        final HashMap<String,Object> cartmap = new HashMap<>();

        cartmap.put("name_product",name.getText().toString());
        cartmap.put("price_product",price.getText().toString());
        cartmap.put("current_time",save_time);
        cartmap.put("current_date",save_date);
        cartmap.put("total_quantity",qty.getText().toString());
        cartmap.put("total_price",totalprice);


        firestore.collection("add_cart").document(auth.getCurrentUser().getUid()).collection("User").add(cartmap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailProduct.this, "Product Added!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
                
    }
}