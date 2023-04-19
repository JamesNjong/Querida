package com.example.querida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.querida.databinding.ActivityFeedbackBinding;
import com.example.querida.databinding.ActivityScanBinding;

public class Feedback extends AppCompatActivity {

    private ActivityFeedbackBinding  binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.feebackData.setText(getIntent().getStringExtra("Results"));
    }
}