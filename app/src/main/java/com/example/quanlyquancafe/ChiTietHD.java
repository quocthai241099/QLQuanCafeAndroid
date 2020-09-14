package com.example.quanlyquancafe;

import java.io.Serializable;

public class ChiTietHD implements Serializable {
    int idhoadon, idmon, soluong;
    float thanhtien;

    public ChiTietHD(int idhoadon, int idmon, int soluong, float thanhtien) {
        this.idhoadon = idhoadon;
        this.idmon = idmon;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
    }

    public ChiTietHD() {
    }

    public int getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(int idhoadon) {
        this.idhoadon = idhoadon;
    }

    public int getIdmon() {
        return idmon;
    }

    public void setIdmon(int idmon) {
        this.idmon = idmon;
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
