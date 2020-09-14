package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class ManHinhChinh extends AppCompatActivity {
    TaiKhoan tk;
    int phanquyen;
    DataBase dataBase;
    Button btnBanHang, btnThuChi, btnBaoCao, btnCaiDat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        Bundle kq = getIntent().getBundleExtra("data");
        tk =(TaiKhoan)kq.getSerializable("taikhoan");
         phanquyen=tk.getPhanquyen();
         btnBaoCao=findViewById(R.id.btnBaoCao);
         btnBanHang=findViewById(R.id.btnBanHang);

         btnCaiDat = findViewById(R.id.btnCaiDat);
         xuLySuKien();
    }
    private  void xuLySuKien()
    {
        btnBaoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phanquyen==0)
                {
                    Toast.makeText(ManHinhChinh.this,"Bạn không có quyền truy cập chức năng này",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(ManHinhChinh.this,HoaDonActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phanquyen==0)
                {
                    Toast.makeText(ManHinhChinh.this,"Bạn không có quyền truy cập chức năng này",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(ManHinhChinh.this,CaiDatActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnBanHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManHinhChinh.this,BanHangActivity.class);
                startActivity(intent);
            }
        });
    }

}