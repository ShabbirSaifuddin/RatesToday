package com.staffshaw.ratestoday.Model;

import java.util.ArrayList;

public class CottonModel {

    public static final ArrayList<String> Name = new ArrayList<>();
    public static final ArrayList<String> Value = new ArrayList<>();
    public static final ArrayList<String> Time = new ArrayList<>();
    public static final ArrayList<String> Source = new ArrayList<>();
    public static final ArrayList<String> Description = new ArrayList<>();
    public String NAME;
    public String VALUE;
    public String TIME;
    public String SOURCE;
    public String DESCRIPTION;

    public CottonModel(String NAME, String VALUE, String TIME, String SOURCE, String DESCRIPTION) {
        this.NAME = NAME;
        this.VALUE = VALUE;
        this.TIME = TIME;
        this.SOURCE = SOURCE;
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String base) {
        this.NAME = base;
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

    public String getSOURCE() {
        return SOURCE;
    }

    public void setSOURCE(String source) {
        this.SOURCE = source;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String description) {
        this.DESCRIPTION = description;
    }
}
