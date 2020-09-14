package com.example.quanlyquancafe;

import java.io.Serializable;

public class LoaiMon implements Serializable {
    int id;
    String tenloai;

    public LoaiMon(int id, String tenloai) {
        this.id = id;
        this.tenloai = tenloai;
    }

    public LoaiMon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
