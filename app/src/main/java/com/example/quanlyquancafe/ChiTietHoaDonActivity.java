package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ChiTietHoaDonActivity extends AppCompatActivity {
    ArrayList<ChiTietHD> arrayList;
    AdapterChiTietHD adapterChiTietHD;
    ListView lvCT;
    DataBase dataBase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        Bundle kq = getIntent().getBundleExtra("data");
        int id=(int)kq.getInt("idhoadon");
        lvCT=findViewById(R.id.lvCTHD);
        arrayList = new ArrayList<>();
        Cursor cursor = dataBase.GetData("SELECT * FROM ChiTietHD where IDHoaDon="+id+"");
        while (cursor.moveToNext())
        {
            int idhd=cursor.getInt(0);
            int idmon=cursor.getInt(1);
            int soluong=cursor.getInt(2);
            float thanhtien=cursor.getFloat(3);
            ChiTietHD  chiTietHD = new ChiTietHD(idhd,idmon,soluong,thanhtien);
            arrayList.add(chiTietHD);
        }
        adapterChiTietHD = new AdapterChiTietHD(ChiTietHoaDonActivity.this,R.layout.hienthicthd,arrayList);
        lvCT.setAdapter(adapterChiTietHD);
    }
}