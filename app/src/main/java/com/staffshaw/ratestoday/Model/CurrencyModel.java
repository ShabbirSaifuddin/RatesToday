package com.staffshaw.ratestoday.Model;

public class CurrencyModel {

    public String BASE;
    public String KEY;
    public String VALUE;
    public String TIME;

    public CurrencyModel(String BASE, String KEY, String VALUE, String TIME) {
        this.BASE = BASE;
        this.KEY = KEY;
        this.VALUE = VALUE;
        this.TIME = TIME;
    }

    public String getBASE() {
        return BASE;
    }

    public void setBASE(String base) {
        this.BASE = base;
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String key) {
        this.KEY = key;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String value) {
        this.VALUE = value;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String time) {
        this.TIME = time;
    }
}
