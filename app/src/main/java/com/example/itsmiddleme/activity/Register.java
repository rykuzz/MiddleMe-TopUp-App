package com.example.itsmiddleme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itsmiddleme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText name,email,password;
    private FirebaseAuth auth;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){
            startActivity(new Intent(Register.this,MainActivity.class));
            finish();
        }

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        sharedPreferences = getSharedPreferences("onBoardingPage",MODE_PRIVATE);
        boolean isfirsttime = sharedPreferences.getBoolean("firsttime",true);

        if(isfirsttime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firsttime",false);
            editor.commit();

            Intent intent = new Intent(Register.this, OnBoarding.class);
            startActivity(intent);
            finish();
        }
    }

    public void signup(View view) {

        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userpassword = password.getText().toString();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please Enter your Name!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(useremail)){
            Toast.makeText(this,"Please Enter your Email!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userpassword)){
            Toast.makeText(this,"Please Enter your Password!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(userpassword.length()<6){
            Toast.makeText(this,"Password too short!, add more characters! (min 6)",Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Register Succesfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,MainActivity.class));

                }else{
                    Toast.makeText(Register.this, "Registration Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

//        startActivity(new Intent(Register.this,MainActivity.class
//        ));
    }

    public void signin(View view) {
        startActivity(new Intent(Register.this,Login.class
        ));
    }
}