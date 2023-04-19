package com.example.querida;

import static com.example.querida.firebase.DataStores.GuestList;
import static com.example.querida.firebase.DataStores.TableList;
import static com.example.querida.firebase.DataStores.updateTable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.querida.databinding.ActivityEditTableBinding;
import com.example.querida.firebase.Person;
import com.example.querida.firebase.TableChairs;

public class EditTableActivity extends AppCompatActivity {


    private ActivityEditTableBinding binding;
    private int position;
    private TableChairs thisTable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getIntent().getIntExtra("Position",0);
        thisTable = TableList.get(position);
        binding = ActivityEditTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.tableName.getText().toString().trim(),
                        owner = binding.tableOwner.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(owner)){
                    thisTable.setTag(name);
                    thisTable.setOwner(owner);
                    updateTable(thisTable);
                }
                onBackPressed();
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.tableName.setText(thisTable.getTag());
        binding.tableOwner.setText(thisTable.getOwner());
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,TableDetails.class);
        intent.putExtra("Position",position);
        startActivity(intent);
        finish();
    }





}