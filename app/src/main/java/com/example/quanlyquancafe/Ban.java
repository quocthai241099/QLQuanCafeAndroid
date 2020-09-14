package com.example.quanlyquancafe;

import java.io.Serializable;

public class Ban implements Serializable {
    int id;
    String tenban, trangthai;

    public Ban(int id, String tenban, String trangthai) {
        this.id = id;
        this.tenban = tenban;
        this.trangthai = trangthai;
    }

    public Ban() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
