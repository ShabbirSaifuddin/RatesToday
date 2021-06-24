package com.staffshaw.ratestoday.Model;

public class CryptoModel {


    public String NAME;
    public String KEY;
    public String RANK;
    public String TIME;
    public String PRICE;

    public CryptoModel(String NAME, String KEY, String RANK, String TIME, String PRICE) {
        this.NAME = NAME;
        this.KEY = KEY;
        this.RANK = RANK;
        this.TIME = TIME;
        this.PRICE = PRICE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String name) {
        this.NAME = name;
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String key) {
        this.KEY = key;
    }

    public String getRANK() {
        return RANK;
    }

    public void setRANK(String rank) {
        this.RANK = rank;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String time) {
        this.TIME = time;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String price) {
        this.PRICE = price;
    }

}

