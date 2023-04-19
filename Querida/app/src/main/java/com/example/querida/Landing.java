package com.example.querida;

import static com.example.querida.firebase.DataStores.GuestList;
import static com.example.querida.firebase.DataStores.ValidateScan;
import static com.example.querida.firebase.DataStores.getTableForPerson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.querida.adapters.TablesRecyclerViewAdapter;
import com.example.querida.databinding.ActivityLandingBinding;
import com.example.querida.firebase.Person;
import com.example.querida.firebase.TableChairs;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



public class Landing extends AppCompatActivity  implements TablesRecyclerViewAdapter.OnItemClickListener {
    private ActivityLandingBinding binding;
    private TablesRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(Landing.this);
                intentIntegrator.setPrompt("JAELLA2022 | GUEST SCAN");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Landing.this, People.class));
            }
        });

        loadTableView();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult.getContents() != null){

            int pos = ValidateScan(intentResult.getContents());

            if (pos == -1){
                Intent intent = new Intent(this,Feedback.class);
                intent.putExtra("Results", intentResult.getContents());
                startActivity(intent);

            }else {
               Person person = GuestList.get(pos);
                TableChairs tab = getTableForPerson(person.getTable());
                if (tab.getTag().equalsIgnoreCase("Unknown") || tab.getOwner().equalsIgnoreCase("Unknown")){
                    Intent intent = new Intent(this,PersonEditActivity.class);
                    intent.putExtra("Position", pos);
                    intent.putExtra("FromScan", 10);
                    startActivity(intent);
                }
                else if (GuestList.get(pos).getName().equalsIgnoreCase("Unknown")){
                    Intent intent = new Intent(this,PersonEditActivity.class);
                    intent.putExtra("Position", pos);
                    intent.putExtra("FromScan", 10);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this,Scan.class);
                    intent.putExtra("Position", pos);
                    intent.putExtra("fromList", 0);
                    startActivity(intent);
                }

            }
        }else{
            Toast.makeText(this, "Nothing was scanned", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, TableDetails.class);
        intent.putExtra("Position",position);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadTableView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadTableView();
    }

    private void loadTableView(){
        recyclerView = binding.listView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter = new TablesRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }
}