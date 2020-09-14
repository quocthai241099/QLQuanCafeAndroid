package com.example.quanlyquancafe;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    String user;

    public TaiKhoan(String user, String pass, String hoten, int phanquyen) {
        this.user = user;
        this.pass = pass;
        this.hoten = hoten;
        this.phanquyen = phanquyen;
    }

    String pass;
    String hoten;
    int phanquyen;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getPhanquyen() {
        return phanquyen;
    }

    public void setPhanquyen(int phanquyen) {
        this.phanquyen = phanquyen;
    }

    public TaiKhoan() {
    }
}
