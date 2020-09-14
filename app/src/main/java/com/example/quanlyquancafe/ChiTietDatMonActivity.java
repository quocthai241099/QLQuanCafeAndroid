package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChiTietDatMonActivity extends AppCompatActivity {
    ListView lvChiTietDatMon;
    ArrayList<ChiTietDatMon> chiTietDatMons;
    DataBase dataBase;
    AdapterChiTietDatMon da;
    Bundle kq;
    Button btnTamTinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dat_mon);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        lvChiTietDatMon=findViewById(R.id.lvChiTietDatMon);
       kq = getIntent().getBundleExtra("data");
        chiTietDatMons =(ArrayList<ChiTietDatMon>)kq.getSerializable("chitiet");
        btnTamTinh=findViewById(R.id.btnTamTinh);
        hienThi();
        xuLySuKien();
    }
    private void xuLySuKien()
    {
        btnTamTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    int idban = chiTietDatMons.get(0).idban;
                    int idmon;
                    float thanhtien;
                    // Toast.makeText(ChiTietDatMonActivity.this,idban+"",Toast.LENGTH_SHORT).show();
                    float tongtien = 0;
                    for (int i = 0; i < chiTietDatMons.size(); i++) {
                        tongtien = tongtien + chiTietDatMons.get(i).thanhtien;
                    }
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    String str = format.format(date);
                    Date thoigian = new SimpleDateFormat("dd/MM/yyyy").parse(str);
                   dataBase.QueryData("INSERT INTO HoaDon values(null,'" + thoigian + "'," + idban + ",0,0)");

                   Cursor cursor = dataBase.GetData("SELECT * FROM HoaDon");
                    cursor.moveToLast();
                    int soluong;
                    int idhd = cursor.getInt(0);
                  //  Toast.makeText(ChiTietDatMonActivity.this,idhd+"",Toast.LENGTH_SHORT).show();
                   for (int i = 0; i < chiTietDatMons.size(); i++) {
                      idmon = chiTietDatMons.get(i).idmon;
                     thanhtien = chiTietDatMons.get(i).thanhtien;
                        soluong=chiTietDatMons.get(i).soluong;
                      dataBase.QueryData("INSERT INTO ChiTietHD values("+idhd+","+idmon+","+soluong+","+thanhtien+")");
                  }
                    dataBase.QueryData("UPDATE Ban set TrangThai='Có người' where ID=" + idban + "");
                    Intent intent = new Intent(ChiTietDatMonActivity.this, BanHangActivity.class);
                    Bundle bd = new Bundle();
                    bd.putInt("idhoadon",idhd);
                    intent.putExtra("data",bd);
                    setResult(RESULT_OK);
                    startActivity(intent);
                    finish();

                }
                catch (Exception ex)
                {
                    Toast.makeText(ChiTietDatMonActivity.this,"Chưa chọn món",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void hienThi()
    {

        da = new AdapterChiTietDatMon(ChiTietDatMonActivity.this,R.layout.hienthichitietdatmon,chiTietDatMons);
        lvChiTietDatMon.setAdapter(da);
    }
}