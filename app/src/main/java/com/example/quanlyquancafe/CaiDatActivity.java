package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CaiDatActivity extends AppCompatActivity {
    Button btnLoaiMon, btnBan, btnNguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);
        btnLoaiMon=findViewById(R.id.btnLoaiMon);
        btnBan=findViewById(R.id.btnQuanLyBan);
        btnNguoiDung=findViewById(R.id.btnQuanLyNguoiDung);

        xuLySuKien();
    }
    private void xuLySuKien()
    {
        btnLoaiMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaiDatActivity.this,LoaiMonActivity.class);
                startActivity(intent);
            }
        });
        btnBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaiDatActivity.this,BanActivity.class);
                startActivity(intent);
            }
        });
        btnNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaiDatActivity.this,NguoiDungActivity.class);
                startActivity(intent);
            }
        });

    }
}