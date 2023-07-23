package com.example.itsmiddleme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itsmiddleme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddOrder extends AppCompatActivity {

    EditText name,address,city,postalcode,phonenumber;
    Toolbar toolbar;
    Button addorderbtn;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        toolbar = findViewById(R.id.add_address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.ad_name);
        address = findViewById(R.id.ad_address);
        city = findViewById(R.id.ad_city);
        phonenumber = findViewById(R.id.ad_phone);
        postalcode = findViewById(R.id.ad_code);
        addorderbtn = findViewById(R.id.ad_add_address);

        addorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String usercity = city.getText().toString();
                String useraddress = address.getText().toString();
                String usercode = postalcode.getText().toString();
                String usernumber = phonenumber.getText().toString();
                String order_point = "";

                if(!username.isEmpty()){
                    order_point+=username;
                }
                if(!usercity.isEmpty()){
                    order_point+=usercity;
                }
                if(!useraddress.isEmpty()){
                    order_point+=useraddress;
                }
                if(!usercode.isEmpty()){
                    order_point+=usercode;
                }
                if(!usernumber.isEmpty()){
                    order_point+=usernumber;
                }
                if(!username.isEmpty() && !usercity.isEmpty() && !useraddress.isEmpty() && !usercode.isEmpty() && !usernumber.isEmpty()  ){
                    Map<String,String> map = new HashMap<>();
                    map.put("orderpoint",order_point);

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(AddOrder.this, "Order Confirmed!", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(AddOrder.this,DetailProduct.class));
//                                finish();
                            }else{
                                Toast.makeText(AddOrder.this, "Please fill the order form!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}