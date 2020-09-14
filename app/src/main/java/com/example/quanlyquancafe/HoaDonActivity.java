package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonActivity extends AppCompatActivity {
    ArrayList<HoaDon> arrayList;
    ListView lvHoaDon;
    AdapterHoaDon da;
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        lvHoaDon=findViewById(R.id.lvHoaDon);
        hienThi();
        xuLySuKien();
    }
    private void xuLySuKien()
    {
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HoaDonActivity.this,ChiTietHoaDonActivity.class);
                Bundle bd = new Bundle();
                bd.putInt("idhoadon",arrayList.get(i).id);
                intent.putExtra("data",bd);
                startActivity(intent);
            }
        });
    }
    private void hienThi()
    {
        arrayList = new ArrayList<>();
        Cursor cursor=dataBase.GetData("SELECT * from HoaDon");
        while (cursor.moveToNext())
        {
            int id=cursor.getInt(0);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date= new Date(cursor.getString(1));
            String ngay =format.format(date);
            int idban=cursor.getInt(2);
            float tongtien=cursor.getFloat(4);
            HoaDon hd = new HoaDon(id,idban,date,tongtien,0);
            arrayList.add(hd);

        }
        da = new AdapterHoaDon(HoaDonActivity.this,R.layout.hienthihoadon,arrayList);
        lvHoaDon.setAdapter(da);
    }
}