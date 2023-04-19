package com.example.querida.firebase;

public class Record {


    String Category;

    int WAITING, CHECKED;

    public Record() {
    }

    public Record(String category, int WAITING, int CHECKED) {
        Category = category;
        this.WAITING = WAITING;
        this.CHECKED = CHECKED;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getWAITING() {
        return WAITING;
    }

    public void setWAITING(int WAITING) {
        this.WAITING = WAITING;
    }

    public int getCHECKED() {
        return CHECKED;
    }

    public void setCHECKED(int CHECKED) {
        this.CHECKED = CHECKED;
    }
}
