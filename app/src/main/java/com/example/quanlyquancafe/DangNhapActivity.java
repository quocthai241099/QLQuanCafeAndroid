package com.example.quanlyquancafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangNhapActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    Button btnDangNhap, btnThoat, btnCaiDat;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        taoCSDL();
        edtUser=findViewById(R.id.edtUser);
        edtPass=findViewById(R.id.edtPass);
        btnDangNhap=findViewById(R.id.btnDangNhap);
        btnThoat= findViewById(R.id.btnThoat);
        xuLySuKien();

    }

    private void xuLySuKien()
    {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor kiemTraDN=dataBase.GetData("SELECT * FROM TaiKhoan where User='"+edtUser.getText().toString()+"' and Password='"+edtPass.getText().toString()+"'");
                if(kiemTraDN.getCount()>0)
                {
                    kiemTraDN.moveToFirst();
                    Intent intent = new Intent(DangNhapActivity.this,ManHinhChinh.class);
                    String user = kiemTraDN.getString(0);
                    String pass = kiemTraDN.getString(1);
                    String hoten = kiemTraDN.getString(2);
                    int phanquyen=kiemTraDN.getInt(3);
                    TaiKhoan tk =new TaiKhoan(user, pass, hoten, phanquyen);
                    Bundle bd = new Bundle();
                    bd.putSerializable("taikhoan",tk);
                    intent.putExtra("data",bd);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(DangNhapActivity.this,"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DangNhapActivity.this);
                builder.setTitle("Thoát");
                builder.setMessage("Bạn có muốn thoát chương trình không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

            }
        });
    }
    private void taoCSDL()
    {
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS TaiKhoan(User varchar primary key, Password varchar, HoTen varchar, PhanQuyen integer)");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS Ban (ID integer primary key autoincrement, TenBan varchar, TrangThai varchar)");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS LoaiMon(ID integer primary key autoincrement, TenLoai varchar)");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS Mon(ID integer primary key autoincrement, TenMon varchar, GiaBan float, IDLoaiMon integer not null constraint IDLoaiMon references LoaiMon(ID) on delete cascade)");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS HoaDon(ID integer primary key autoincrement,ThoiGian date, IDBan integer not null constraint IDBan references Ban(ID) on delete cascade, GiamGia float, TongTien float)");
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS ChiTietHD(IDHoaDon integer not null constraint IDHoaDon references HoaDon(ID) on delete cascade, IDMon integer not null constraint IDMon references Mon(ID) on delete cascade, SoLuong integer, ThanhTien float, primary key(IDHoaDon, IDMon))");
        //nhapDuLieu();

        }
        private void nhapDuLieu()
        {
            //nhập liệu
            //Bảng TaiKhoan
             dataBase.QueryData("INSERT INTO TaiKhoan values('admin','123','Trương Quốc Thái',1)");
             dataBase.QueryData("INSERT INTO TaiKhoan values('tho','123','Lê Hữu Thọ',1)");
             dataBase.QueryData("INSERT INTO TaiKhoan values('hieu','123','hieu',0)");

            //bảng Ban
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 1','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 2','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 3','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 4','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 5','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 6','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 7','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 8','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 9','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 10','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 11','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 12','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 13','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 14','Trống')");
            dataBase.QueryData("INSERT INTO Ban values(null,'Bàn 15','Trống')");
           //bảng LoaiMon
            dataBase.QueryData("INSERT INTO LoaiMon values(null,'Freeze')");
            dataBase.QueryData("INSERT INTO LoaiMon values(null,'Cà phê')");
            dataBase.QueryData("INSERT INTO LoaiMon values(null,'Trà')");
             dataBase.QueryData("INSERT INTO LoaiMon values(null,'Bánh mì')");
            dataBase.QueryData("INSERT INTO LoaiMon values(null,'Khác')");
           //bảng Mon
            dataBase.QueryData("INSERT INTO Mon values(null,'Phin sữa đá',29000,1)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Phin đen đá',29000,1)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Phin đen nóng',29000,1)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Phin sữa nóng',29000,1)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Mocha Macchiato',59000,1)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Espresso',44000,1)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Americano',44000,1)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Latte',54000,1)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Caramel Phin Freeze',49000,2)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Classic Phin Freeze',49000,2)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Freeze Trà Xanh',49000,2)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Cookies & Cream',49000,2)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Freeze Sô-cô-la',49000,2)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Trà Sen Vàng',39000,3)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Trà Thạch Vải',39000,3)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Trà Thạch Đào',39000,3)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Trà Thanh Đào',39000,3)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Thịt nướng',19000,4)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Xíu mại',19000,4)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Gà Xé Nước Tương',19000,4)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Chả lụa xá xíu',19000,4)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Bánh Mousse Cacao',29000,5)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Bánh Sô-cô-la Highlands',29000,5)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Bánh Caramel Phô Mai',29000,5)");
            dataBase.QueryData("INSERT INTO Mon values(null,'Bánh Mousse Đào',29000,5)");

        }


}