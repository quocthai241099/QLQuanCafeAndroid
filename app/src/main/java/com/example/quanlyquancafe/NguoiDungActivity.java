package com.example.quanlyquancafe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;

public class NguoiDungActivity extends AppCompatActivity {
    DataBase dataBase;
    ArrayList<TaiKhoan> lstTaiKhoan;
    ListView lvNguoiDung;
    AdapterTaiKhoan da;
    int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        lvNguoiDung=findViewById(R.id.lvNguoiDung);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);

        hienThi();
        xuLySuKien();
    }
    private void xuLySuKien()
    {
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final int index=i;
                final Dialog dialogQuanLyNguoiDung = new Dialog(NguoiDungActivity.this);
                dialogQuanLyNguoiDung.setContentView(R.layout.dialog_suaxoa);
                dialogQuanLyNguoiDung.setCanceledOnTouchOutside(false);
                dialogQuanLyNguoiDung.show();
                TextView tvQuanLyNguoiDung = dialogQuanLyNguoiDung.findViewById(R.id.tvQuanLyLoaiMon);
                tvQuanLyNguoiDung.setText("Quản lý người dùng");
                Button btnSuaNguoiDung=dialogQuanLyNguoiDung.findViewById(R.id.btnSuaLoaiMon);
                Button btnXoaNguoiDung = dialogQuanLyNguoiDung.findViewById(R.id.btnXoaLoaiMon);
                Button btnHuyQuanLyNguoiDung = dialogQuanLyNguoiDung.findViewById(R.id.btnHuySuaXoaLoaiMon);
                btnSuaNguoiDung.setText("Thay đổi thông tin người dùng");
                btnXoaNguoiDung.setText("Xóa người dùng");
                btnHuyQuanLyNguoiDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogQuanLyNguoiDung.dismiss();
                    }
                });
                btnSuaNguoiDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TaiKhoan taiKhoan = new TaiKhoan(lstTaiKhoan.get(index).user,lstTaiKhoan.get(index).pass,lstTaiKhoan.get(index).hoten,lstTaiKhoan.get(index).phanquyen);
                        Intent intent = new Intent(NguoiDungActivity.this,SuaNguoiDungActivity.class);
                        Bundle bd = new Bundle();
                        bd.putSerializable("taikhoan",taiKhoan);
                        intent.putExtra("data",bd);
                        startActivityForResult(intent,REQUEST_CODE);
                        dialogQuanLyNguoiDung.dismiss();
                    }
                });
                btnXoaNguoiDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogQuanLyNguoiDung.dismiss();
                        final Dialog dialogXoaNguoiDung = new Dialog(NguoiDungActivity.this);
                        dialogXoaNguoiDung.setCanceledOnTouchOutside(false);
                        dialogXoaNguoiDung.setContentView(R.layout.dialog_xoaloaimon);
                        dialogXoaNguoiDung.show();
                        dialogXoaNguoiDung.dismiss();
                        TextView tvXoaNguoiDung = dialogXoaNguoiDung.findViewById(R.id.tieuDeXoaLoaiMon);
                        tvXoaNguoiDung.setText("Xóa người dùng");
                        Button btnXoa = dialogXoaNguoiDung.findViewById(R.id.btnLuuXoaLoaiMon);
                        Button btnHuyXoaNguoiDung = dialogXoaNguoiDung.findViewById(R.id.btnHuyXoaLoaiMon);
                        TextView userXoa = dialogXoaNguoiDung.findViewById(R.id.tvTenLoaiMonXoa);
                        btnHuyXoaNguoiDung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogQuanLyNguoiDung.dismiss();
                                dialogXoaNguoiDung.dismiss();
                            }
                        });


                            userXoa.setText(lstTaiKhoan.get(index).user);
                            final Dialog dialogXacNhanXoa = new Dialog(NguoiDungActivity.this);
                            dialogXacNhanXoa.setContentView(R.layout.dialog_xacnhanxoa);
                            dialogXacNhanXoa.setCanceledOnTouchOutside(false);
                            dialogXacNhanXoa.show();
                            TextView tvXacNhanXoa = dialogXacNhanXoa.findViewById(R.id.tvXacNhanXoa);
                            tvXacNhanXoa.setText("Bạn có muốn xóa tài khoản "+lstTaiKhoan.get(index).user+" không?");
                            Button btnCo = dialogXacNhanXoa.findViewById(R.id.btnXacNhanXoa);
                            Button btnKhong = dialogXacNhanXoa.findViewById(R.id.btnHuyXacNhanXoa);
                            btnKhong.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogQuanLyNguoiDung.dismiss();
                                    dialogXacNhanXoa.dismiss();
                                    dialogXoaNguoiDung.dismiss();
                                }
                            });
                            btnCo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogQuanLyNguoiDung.dismiss();
                                    dialogXacNhanXoa.dismiss();
                                    dialogXoaNguoiDung.dismiss();
                                    dataBase.QueryData("DELETE FROM TaiKhoan where User='"+lstTaiKhoan.get(index).user+"'");
                                    hienThi();
                                }
                            });




                    }
                });

            }
        });
    }
    private void hienThi()
    {
        lstTaiKhoan=new ArrayList<>();
        Cursor cursor = dataBase.GetData("SELECT * FROM TaiKhoan");
        while (cursor.moveToNext())
        {
            String user=cursor.getString(0);
            String pass=cursor.getString(1);
            String hoten=cursor.getString(2);
            int phanquyen=cursor.getInt(3);
            TaiKhoan tk = new TaiKhoan(user, pass, hoten, phanquyen);
            lstTaiKhoan.add(tk);
        }
        da = new AdapterTaiKhoan(NguoiDungActivity.this,R.layout.hienthinguoidung,lstTaiKhoan);
        lvNguoiDung.setAdapter(da);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnu_back,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(NguoiDungActivity.this,DangKyActivity.class);
        startActivityForResult(intent,REQUEST_CODE);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        hienThi();
        super.onActivityResult(requestCode, resultCode, data);
    }
}