package com.example.quanlyquancafe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BanHangActivity extends AppCompatActivity {
    DataBase dataBase;
    ArrayList<Ban> lstBan;
    GridView grvBan;
    AdapterBan adapterBan;
    int REQUEST_CODE=1;
    int idhd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_hang);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        grvBan=(GridView)findViewById(R.id.grvBanHang);

        xuLySuKien();
        hienThi();
    }
    private void xuLySuKien()
    {
        grvBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BanHangActivity.this,DatMonActivity.class);
                Ban ban = new Ban(lstBan.get(i).id,lstBan.get(i).tenban,lstBan.get(i).trangthai );
                if(ban.getTrangthai().toString().equals("Trống")) {
                    Bundle bd = new Bundle();
                    bd.putSerializable("ban", ban);
                    intent.putExtra("data", bd);
                    startActivityForResult(intent, REQUEST_CODE);
                }
                else
                {

                   // Toast.makeText(BanHangActivity.this,lstBan.get(i).id+"",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(BanHangActivity.this,TamTinhActivity.class);
                    Bundle bd = new Bundle();
                    bd.putSerializable("ban", ban);
                    intent1.putExtra("data", bd);
                    startActivityForResult(intent1, REQUEST_CODE);
                }
            }
        });
    }
    private void hienThi()
    {

        Cursor cursor = dataBase.GetData("SELECT * from Ban");
        lstBan = new ArrayList<>();
        while(cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenban=cursor.getString(1);
            String trangthai=cursor.getString(2);
            Ban ban = new Ban(id,tenban,trangthai);
            lstBan.add(ban);
        }

        adapterBan = new AdapterBan(BanHangActivity.this,R.layout.hienthiban,lstBan);
        grvBan.setAdapter(adapterBan);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            hienThi();

        super.onActivityResult(requestCode, resultCode, data);
    }
}