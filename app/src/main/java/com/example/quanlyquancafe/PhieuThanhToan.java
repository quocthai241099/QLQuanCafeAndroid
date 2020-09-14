package com.example.quanlyquancafe;

import java.io.Serializable;

public class PhieuThanhToan implements Serializable {
    String tenmon;
    int soluong;
    float dongia, thanhtien;

    public PhieuThanhToan(String tenmon, int soluong, float dongia, float thanhtien) {
        this.tenmon = tenmon;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public PhieuThanhToan() {
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

    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public float getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(float thanhtien) {
        this.thanhtien = thanhtien;
    }
}
