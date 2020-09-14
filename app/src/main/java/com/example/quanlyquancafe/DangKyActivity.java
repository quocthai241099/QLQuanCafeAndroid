package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class DangKyActivity extends AppCompatActivity {
    DataBase dataBase;
    Button btnDangKy, btnHuyDangKy;
    EditText edtHoTen, edtUser, edtPass, edtNhapPass;
    RadioButton radAdmin, radBanHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        btnDangKy=findViewById(R.id.btnDangKyDK);
        btnHuyDangKy=findViewById(R.id.btnThoatDK);
        edtHoTen=findViewById(R.id.edtHoTen);
        edtUser=findViewById(R.id.edtUserDK);
        edtPass=findViewById(R.id.edtPassDK);
        edtNhapPass=findViewById(R.id.edtConfirmPass);
        radAdmin=findViewById(R.id.radAdmin);
        radBanHang=findViewById(R.id.radBanHang);
        xuLySuKien();
    }
    private  void xuLySuKien()
    {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtHoTen.getText().toString())||TextUtils.isEmpty(edtNhapPass.getText().toString())||TextUtils.isEmpty(edtPass.getText().toString())||TextUtils.isEmpty(edtUser.getText().toString()))
                {
                    Toast.makeText(DangKyActivity.this,"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(edtPass.getText().toString().equals(edtNhapPass.getText().toString()))
                    {
                        Cursor cursor = dataBase.GetData("SELECT * FROM TaiKhoan where User='"+edtUser.getText().toString()+"'");
                        if(cursor.getCount()>0)
                        {
                            Toast.makeText(DangKyActivity.this,"Đã trùng user name",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String hoten=edtHoTen.getText().toString();
                            String username=edtUser.getText().toString();
                            String password = edtNhapPass.getText().toString();
                            int phanquyen;
                            if(radAdmin.isChecked())
                                phanquyen=1;
                            else
                                phanquyen=0;

                            dataBase.QueryData("INSERT INTO TaiKhoan values('"+username+"','"+password+"','"+hoten+"',"+phanquyen+")");
                            Intent intent = new Intent();
                            finish();

                        }
                    }
                    else
                    {
                        Toast.makeText(DangKyActivity.this,"Nhập lại mật khẩu không đúng",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        btnHuyDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}