package com.example.querida.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.querida.firebase.Person;
import com.example.querida.firebase.TableChairs;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataStores {

    public static ArrayList<Person> GuestList = new ArrayList<>();
    public static ArrayList<String> guestKeys = new ArrayList<>();

    public static ArrayList<TableChairs> TableList = new ArrayList<>();
    public static ArrayList<String> tableKeys = new ArrayList<>();

    public static ArrayList<Record> RecordsList = new ArrayList<>();


    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference myRef = database.getReference("Querida");

    public static Person theCurrentPerson;
    public static TableChairs theCurrentTable;


    public static void loadData_ds(){
        ClearRecords();
        myRef.child("People").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Person guest = ds.getValue(Person.class);

                    if (guest.getCode().substring(0,30).contains("P0") || guest.getCode().substring(0,30).contains("P10")){
                        GuestList.add(guest);
                        guestKeys.add(guest.getCode());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.keepSynced(true);
        myRef.child("Tables").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    TableChairs tc = ds.getValue(TableChairs.class);
                    TableList.add(tc);
                    tableKeys.add(tc.getNumber());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef.keepSynced(true);
    }

    public static ArrayList<Person> getTablers(String table){
        ArrayList<Person> tablers = new ArrayList<>();

        for (int i=0; i<GuestList.size(); i++){
            Person pers = GuestList.get(i);
            if (pers.getTable().equals(table)){
                tablers.add(pers);
            }
        }

        return  tablers;
    }


    public static void updateTable(TableChairs table){
        DatabaseReference ref = myRef.child("Tables").child(table.getNumber());
        ref.setValue(table);

        loadData_ds();
    }

    public static void CheckinGuest(Person pers, TableChairs table){
        DatabaseReference ref = myRef.child("People").child(pers.getCode());
        ref.setValue(pers);

        DatabaseReference mRef = myRef.child("Tables").child(pers.getTable());
        mRef.setValue(table);

        loadData_ds();
    }

    public static void updatePerson(Person pers){
        DatabaseReference ref = myRef.child("People").child(pers.getCode());
        ref.setValue(pers);
        loadData_ds();
    }


    public static int ValidateScan(String code){

        int position = guestKeys.indexOf(code);

        if (position >= 0){
            theCurrentPerson = GuestList.get(position);
            theCurrentTable = TableList.get(tableKeys.indexOf(theCurrentPerson.getTable()));
            return position;
        }

        return -1;
    }


    private static void ClearRecords(){
        GuestList.clear();
        guestKeys.clear();
        TableList.clear();
        tableKeys.clear();
    }


    public static TableChairs getTableForPerson(String table){
        int pos = tableKeys.indexOf(table);
        return TableList.get(pos);
    }




    public static void getRecords(){
        myRef.child("Records").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Record record = ds.getValue(Record.class);
                    RecordsList.add(record);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





    //search managers

    public static ArrayList<Person> SearchList = new ArrayList<>();

    public static boolean seachData(String var){
        SearchList.clear();
        boolean status = false;
        for (int i =0; i<GuestList.size(); i++){
            Person pers = GuestList.get(i);
            if (pers.getName().toLowerCase().contains(var.toLowerCase())){
                SearchList.add(pers);
            }else if (getTableForPerson(pers.getTable()).getTag().toLowerCase().contains(var.toLowerCase())){
                SearchList.add(pers);
            }
        }

        if (SearchList.size() >0){
            status = true;
        }

        return status;
    }





    String[] RandomPeople ={
            "Mr & Mme MISSELEL", "Mr & Mme SEMBI","Mr & Mme TACTOUM Bruno","Mr & Mme TAUBE","Mr & Mme BILONG","Mr & Mme SINGHA",
    };
}
