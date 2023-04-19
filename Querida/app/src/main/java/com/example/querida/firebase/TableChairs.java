package com.example.querida.firebase;

public class TableChairs {


    String number,tag,owner,cateory;
    int priority, max_capacity,current_capacity;

    public TableChairs() {
    }

    public TableChairs(String number, String tag, String owner, String cateory, int priority, int max_capacity, int current_capacity) {
        this.number = number;
        this.tag = tag;
        this.owner = owner;
        this.cateory = cateory;
        this.priority = priority;
        this.max_capacity = max_capacity;
        this.current_capacity = current_capacity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCateory() {
        return cateory;
    }

    public void setCateory(String cateory) {
        this.cateory = cateory;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public int getCurrent_capacity() {
        return current_capacity;
    }

    public void setCurrent_capacity(int current_capacity) {
        this.current_capacity = current_capacity;
    }
}
