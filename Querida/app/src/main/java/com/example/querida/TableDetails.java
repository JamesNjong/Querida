package com.example.querida;

import static com.example.querida.firebase.DataStores.GuestList;
import static com.example.querida.firebase.DataStores.TableList;
import static com.example.querida.firebase.DataStores.getTablers;
import static com.example.querida.firebase.DataStores.guestKeys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.querida.adapters.PeopleAdapter;
import com.example.querida.databinding.ActivityScanBinding;
import com.example.querida.databinding.ActivityTableDetailsBinding;
import com.example.querida.firebase.Person;
import com.example.querida.firebase.TableChairs;

import java.util.ArrayList;

public class TableDetails extends AppCompatActivity implements PeopleAdapter.OnItemClickListener {

    private ActivityTableDetailsBinding binding;
    private PeopleAdapter pad;
    private int position;
    private TableChairs thisTable;
    ArrayList<Person> tablers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getIntent().getIntExtra("Position",0);
        thisTable = TableList.get(position);
        binding = ActivityTableDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableDetails.this,EditTableActivity.class);
                intent.putExtra("Position",position);
                startActivity(intent);
                finish();
            }
        });
        RecyclerView recyclerView = binding.listView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tablers = getTablers(thisTable.getNumber());
        pad = new PeopleAdapter(tablers);
        recyclerView.setAdapter(pad);

        pad.setClickListener(this);


        binding.tableNumber.setText(thisTable.getNumber());
        binding.tableName.setText(thisTable.getTag());
        binding.tableTag.setText(thisTable.getOwner());
    }



    @Override
    public void onClick(View view, int positions) {
        Person person= tablers.get(positions);
        Intent intent = new Intent(this, PersonEditActivity.class);
        intent.putExtra("Position",guestKeys.indexOf(person.getCode()));
        intent.putExtra("Table",position);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,Landing.class));
    }
}