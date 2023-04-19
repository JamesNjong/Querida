package com.example.querida;

import static com.example.querida.firebase.DataStores.GuestList;
import static com.example.querida.firebase.DataStores.tableKeys;
import static com.example.querida.firebase.DataStores.updatePerson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;

import com.example.querida.databinding.ActivityPersonEditBinding;
import com.example.querida.firebase.Person;

public class PersonEditActivity extends AppCompatActivity {

    private ActivityPersonEditBinding binding;
    private int persplace;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        persplace = getIntent().getIntExtra("Position", 0);
        person = GuestList.get(persplace);
        binding = ActivityPersonEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.peaPersonName.setText(person.getName());
        binding.peaPersonContact.setText(person.getNumber());

        switch (person.getRelationship()){
            case "Groom":
                binding.peaRadGroom.setChecked(true);
                break;
            case "Bride":
                binding.peaRadBride.setChecked(true);
                break;
            case "Friend":
                binding.peaRadFriends.setChecked(true);
                break;
            default:
                binding.peaRadStrangers.setChecked(true);
                break;
        }

        binding.peaTable.setText(person.getTable());
        binding.peaSpot.setText(person.getCode().substring(0,9));
        binding.peaCode.setText(person.getCode());

        binding.peaRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.pea_rad_groom:
                        person.setRelationship("Groom");
                        break;
                    case R.id.pea_rad_bride:
                        person.setState("Bride");
                        break;
                    case R.id.pea_rad_friends:
                        person.setRelationship("Friend");
                        break;
                    default:
                        person.setRelationship("Stranger");
                        break;
                }
            }
        });


        binding.peaBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.peaUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.peaPersonName.getText().toString().trim();
                String number = binding.peaPersonContact.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)){

                }else{
                    person.setName(name);
                    person.setNumber(number);
                    updatePerson(person);
                    onBackPressed();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent ;
        int scan = getIntent().getIntExtra("FromScan",0);
        if (scan == 10){
            intent = new Intent(this, Landing.class);
        }else{
            intent= new Intent(this, TableDetails.class);
            intent.putExtra("Position",getIntent().getIntExtra("Table",0));
        }

        startActivity(intent);
        finish();
    }


}