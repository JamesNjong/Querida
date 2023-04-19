package com.example.querida;

import static com.example.querida.firebase.DataStores.GuestList;

import android.content.Intent;
import android.os.Bundle;

import com.example.querida.Scan;
import com.example.querida.adapters.PeopleAdapter;
import com.example.querida.adapters.PersonsRecyclerViewAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.querida.databinding.ActivityPeopleBinding;

import com.example.querida.R;

public class People extends AppCompatActivity implements PeopleAdapter.OnItemClickListener{



    private AppBarConfiguration appBarConfiguration;
    private ActivityPeopleBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPeopleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(People.this,SearchActivity.class));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.personsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PeopleAdapter pad = new PeopleAdapter(GuestList);
        recyclerView.setAdapter(pad);
        pad.setClickListener(this);

        setTitle(R.string.app_name);
    }



    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, Scan.class);
        intent.putExtra("Position",position);
        intent.putExtra("fromList", 1);
        startActivity(intent);
        finish();
    }




}