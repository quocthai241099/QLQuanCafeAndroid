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

public class SuaNguoiDungActivity extends AppCompatActivity {
    EditText edtHoTen, edtUser, edtPass, edtConfirm;
    RadioButton radAdmin, radBanHang;
    Button btnSua;
    DataBase dataBase;
    TaiKhoan taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_nguoi_dung);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        edtHoTen=findViewById(R.id.edtSuaHoTen);
        edtUser=findViewById(R.id.edtSuaUserDK);
        edtPass=findViewById(R.id.edtSuaPassDK);
        edtConfirm=findViewById(R.id.edtSuaConfirmPass);
        radAdmin=findViewById(R.id.radSuaAdmin);
        radBanHang=findViewById(R.id.radSuaBanHang);
        btnSua=findViewById(R.id.btnDongYSua);
        Bundle kq = getIntent().getBundleExtra("data");
        taiKhoan = (TaiKhoan)kq.getSerializable("taikhoan");
        edtConfirm.setText(taiKhoan.getPass());
        edtPass.setText(taiKhoan.getPass());
        edtUser.setText(taiKhoan.getPass());
        edtHoTen.setText(taiKhoan.getHoten());
        if(taiKhoan.getPhanquyen()==1)
            radAdmin.setChecked(true);
        else
            radBanHang.setChecked(true);
        xuLySuKien();
    }
    private void xuLySuKien()
    {
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtConfirm.getText().toString())||TextUtils.isEmpty(edtHoTen.getText().toString())||TextUtils.isEmpty(edtPass.getText().toString())||TextUtils.isEmpty(edtUser.getText().toString()))
                {
                    Toast.makeText(SuaNguoiDungActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(edtPass.getText().toString().equals(edtConfirm.getText().toString()))
                    {
                        int phanquyen;
                        if(radAdmin.isChecked())
                            phanquyen=1;
                        else
                            phanquyen=0;
                       dataBase.QueryData("UPDATE TaiKhoan SET Password='"+edtPass.getText().toString()+"',HoTen='"+edtHoTen.getText().toString()+"',PhanQuyen="+phanquyen+" where User='"+taiKhoan.user+"'");
                       finish();
                    }
                    else
                    {
                        Toast.makeText(SuaNguoiDungActivity.this, "Bạn nhập lại mật khẩu sai", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}