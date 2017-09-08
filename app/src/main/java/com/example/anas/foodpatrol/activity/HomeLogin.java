package com.example.anas.foodpatrol.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anas.foodpatrol.R;

public class HomeLogin extends AppCompatActivity {
    Button login_but;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_login);


        login_but = (Button) findViewById(R.id.login_but);




        login_but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(HomeLogin.this, Login.class);
                finish();
                startActivity(intent);
            }
        });

    }
}
