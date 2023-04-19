package com.example.querida;

import static com.example.querida.firebase.DataStores.GuestList;
import static com.example.querida.firebase.DataStores.TableList;
import static com.example.querida.firebase.DataStores.loadData_ds;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.querida.databinding.ActivityMainBinding;
import com.example.querida.firebase.Dummy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();



        loadData_ds();
        stage();
    }


    private void stage(){
        Handler hand = new Handler();
        View logo = binding.mainLogo,
                powered = binding.mainPoweredText;
        FirebaseUser currentUser = mAuth.getCurrentUser();
        hand.post(new Runnable() {
            @Override
            public void run() {
                if (logo.getVisibility() != View.VISIBLE){
                    logo.setVisibility(View.VISIBLE);
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                    logo.setAnimation(anim);
                    hand.postDelayed(this,2000);
                }else if (powered.getVisibility() != View.VISIBLE){
                    powered.setVisibility(View.VISIBLE);
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                    powered.setAnimation(anim);
                    hand.postDelayed(this,4000);
                }else if (currentUser == null || GuestList.size() <1 || TableList.size()<1){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Guestlist ="+GuestList.size()+": Tables="+TableList.size(), Toast.LENGTH_SHORT).show();
                }else{
                    // TODO : Make this a conditional statement and manage the account creations
                    Intent intent = new Intent(MainActivity.this, Landing.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

    }


    private void hideStatus(){
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
            try{
                ActionBar actionBar = getActionBar();
                actionBar.hide();
            }catch (Exception e){}

        }
    }


}