package com.example.quanlyquancafe;

import java.io.Serializable;
import java.util.Date;

public class HoaDon implements Serializable {
    int id, idban;
    Date thoigian;

    float tongtien;
    float giamgia;

    public HoaDon(int id, int idban, Date thoigian,  float tongtien, float giamgia) {
        this.id = id;
        this.idban = idban;
        this.thoigian = thoigian;

        this.tongtien = tongtien;
        this.giamgia = giamgia;
    }

    public HoaDon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdban() {
        return idban;
    }

    public void setIdban(int idban) {
        this.idban = idban;
    }

    public Date getThoigian() {
        return thoigian;
    }

    public void setThoigian(Date thoigian) {
        this.thoigian = thoigian;
    }



    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public float getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(float giamgia) {
        this.giamgia = giamgia;
    }
}
