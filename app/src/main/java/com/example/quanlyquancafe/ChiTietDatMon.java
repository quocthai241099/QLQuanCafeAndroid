package com.example.quanlyquancafe;

import java.io.Serializable;

public class ChiTietDatMon implements Serializable {
    String tenmon;
    int soluong;
    float thanhtien;
    int idmon;
    int idban;

    public ChiTietDatMon(String tenmon, int soluong, float thanhtien, int idmon, int idban) {
        this.tenmon = tenmon;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
        this.idmon=idmon;
        this.idban=idban;
    }

    public int getIdban() {
        return idban;
    }

    public void setIdban(int idban) {
        this.idban = idban;
    }

    public ChiTietDatMon() {
    }

    public int getIdmon() {
        return idmon;
    }

    public void setIdmon(int idmon) {
        this.idmon = idmon;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(float thanhtien) {
        this.thanhtien = thanhtien;
    }
}
