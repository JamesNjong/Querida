package com.example.querida.firebase;

public class Person {


    String name,number,state,relationship,code,table;


    public Person(String name, String number, String state, String relationship, String code, String table) {
        this.name = name;
        this.number = number;
        this.state = state;
        this.relationship = relationship;
        this.code = code;
        this.table = table;
    }

    public Person(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
