package com.example.quanlyquancafe;

import java.io.Serializable;

public class Mon implements Serializable {
    int id, idloaimon;
    String tenmon;
    float giaban;

    public Mon(int id, int idloaimon, String tenmon, float giaban) {
        this.id = id;
        this.idloaimon = idloaimon;
        this.tenmon = tenmon;
        this.giaban = giaban;
    }

    public Mon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdloaimon() {
        return idloaimon;
    }

    public void setIdloaimon(int idloaimon) {
        this.idloaimon = idloaimon;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public float getGiaban() {
        return giaban;
    }

    public void setGiaban(float giaban) {
        this.giaban = giaban;
    }
}
