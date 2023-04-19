package com.example.querida;

import static com.example.querida.firebase.DataStores.GuestList;
import static com.example.querida.firebase.DataStores.loadData_ds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Internet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        loadData_ds();
        stage();

    }




    private void stage(){
        Handler hand = new Handler();

        hand.post(new Runnable() {
            @Override
            public void run() {
                if (GuestList.isEmpty()){
                    hand.postDelayed(this,2000);
                }else{
                    // TODO : Make this a conditional statement and manage the account creations
                    Intent intent = new Intent(Internet.this, Landing.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

    }

}