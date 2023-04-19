package com.example.querida;

import static com.example.querida.firebase.DataStores.CheckinGuest;
import static com.example.querida.firebase.DataStores.GuestList;
import static com.example.querida.firebase.DataStores.TableList;
import static com.example.querida.firebase.DataStores.getTableForPerson;
import static com.example.querida.firebase.DataStores.tableKeys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.querida.adapters.TablesRecyclerViewAdapter;
import com.example.querida.databinding.ActivityScanBinding;
import com.example.querida.firebase.Person;
import com.example.querida.firebase.TableChairs;

import java.io.IOException;

public class Scan extends AppCompatActivity {
    private ActivityScanBinding binding;

    AlertDialog.Builder builder;

    private Person person;
    private int persplace;
    private TableChairs table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        persplace = getIntent().getIntExtra("Position", 0);
        person = GuestList.get(persplace);


        binding = ActivityScanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        processData();




        binding.buttonCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (binding.scanPopup.getVisibility() != View.VISIBLE){
                   checkinGuest();
               }
            }
        });

    }


    private void processData(){
        table = getTableForPerson(person.getTable());
        binding.tableTag.setText(table.getTag());
        binding.tableOwner.setText(table.getOwner());
        binding.tableNumber.setText(table.getNumber());
        binding.tablePerson.setText("Valid");
        setProgress(table);


        if (!table.getNumber().equalsIgnoreCase("Table 50")){
            if (person.getState().equalsIgnoreCase("CHECKED")){
                binding.tableOveruse.setVisibility(View.VISIBLE);
                binding.buttonCheckin.setText("Check Out");
            }else{
                binding.buttonCheckin.setText("Check In");
            }
        }else{
            binding.buttonCheckin.setVisibility(View.GONE);

        }

    }


    private void checkinGuest(){
        if (binding.buttonCheckin.getText().toString().trim().equalsIgnoreCase("CHECK OUT")){
            person.setState("WAITING");
            int count = table.getCurrent_capacity();
            table.setCurrent_capacity(count-1);
            ShowDialogueBox("Are you sure you want to check out guest ?");
        }else{
            person.setState("CHECKED");
            int count = table.getCurrent_capacity();
            table.setCurrent_capacity(count+1);
            ShowDialogueBox("Are you sure you want to check in guest ?");
        }


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int fromWhere = getIntent().getIntExtra("fromList",0);
        if (fromWhere == 1){
            startActivity(new Intent(this, People.class));
        }else if (fromWhere == 2){
            Intent intent = new Intent(this, TableDetails.class);
            intent.putExtra("Position",tableKeys.indexOf(table.getNumber()));
            startActivity(intent);
        }else{
            startActivity(new Intent(this, Landing.class));
        }

        finish();
    }



    private void setProgress(TableChairs table){
        switch (table.getCurrent_capacity()){
            case 0:
                binding.progressFill.setText("0%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_zero));
                break;
            case 1:
                binding.progressFill.setText("10%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_one_two));
                break;
            case 2:
                binding.progressFill.setText("20%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_one_two));
                break;
            case 3:
                binding.progressFill.setText("30%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_three));
                break;
            case 4:
                binding.progressFill.setText("40%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_four));
                break;
            case 5:
                binding.progressFill.setText("50%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_five));
                break;
            case 6:
                binding.progressFill.setText("60%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_six));
                break;
            case 7:
                binding.progressFill.setText("70%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_seven_eight));
                break;
            case 8:
                binding.progressFill.setText("80%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_seven_eight));
                break;
            case 9:
                binding.progressFill.setText("90%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_nine));
                break;
            default:
                binding.progressFill.setText("100%\n FULL");
                binding.progressFill.setBackground(getDrawable(R.drawable.progress_ten));
                break;
        }

    }


    private void ShowDialogueBox(String msg){

        binding.scanPopup.setVisibility(View.VISIBLE);
        binding.popupMsg.setText(msg);
        binding.popCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.scanPopup.setVisibility(View.GONE);
                onBackPressed();
            }
        });
        binding.popContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckinGuest(person,table);
                onBackPressed();
            }
        });

    }



}