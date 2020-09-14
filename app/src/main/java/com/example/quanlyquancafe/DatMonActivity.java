package com.example.quanlyquancafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class DatMonActivity extends AppCompatActivity {
    DataBase dataBase;
    ArrayList<LoaiMon> lstLoaiMon;
    ListView lvLoaiMon;
    ListView lvMon;
    ArrayList<Mon> lstMon;
    AdapterLoaiMonDatMon da;
    AdapterMon daMon;
    int idLoai;
    Ban ban;
    Bundle bundle;
    ArrayList<ChiTietDatMon> chiTietDatMons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_mon);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        lvLoaiMon=findViewById(R.id.lvLoaiMonDatMon);
        lvMon=findViewById(R.id.lvMonDatMon);
        idLoai=-1;
        chiTietDatMons = new ArrayList<>();
        Bundle kq = getIntent().getBundleExtra("data");
        ban =(Ban)kq.getSerializable("ban"  );
        hienThi();
        //hienThiTheoLoai();
        xuLySuKien();
        bundle = new Bundle();
    }
    private void xuLySuKien()
    {
        if(idLoai==-1)
            hienThiMon();
        lvLoaiMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            idLoai=lstLoaiMon.get(i).id;
               hienThiMonTheoLoai(idLoai);
            }
        });
        lvMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialog = new Dialog(DatMonActivity.this  );
                dialog.setContentView(R.layout.dialogdatmon);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                final int index=i;
                Button btnThem = dialog.findViewById(R.id.btnThemMonDatMon);
                final EditText edtSoLuong=dialog.findViewById(R.id.edtSoLuong);
                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(TextUtils.isEmpty(edtSoLuong.getText().toString()))
                        {
                            Toast.makeText(DatMonActivity.this,"Bạn chưa nhập số lượng",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            try {
                                int soluong = Integer.parseInt(edtSoLuong.getText().toString());
                                if(soluong>0)
                                {
                                    int dem=0;
                                    ChiTietDatMon chiTietDatMon = new ChiTietDatMon(lstMon.get(index).tenmon,soluong,soluong*lstMon.get(index).giaban,lstMon.get(index).id,ban.id);
                                    if(chiTietDatMons==null)
                                    {
                                        chiTietDatMons.add(chiTietDatMon);
                                    }
                                    else
                                    {
                                        for(int k = 0;k<chiTietDatMons.size();k++)
                                        {
                                            if(chiTietDatMons.get(k).idmon==chiTietDatMon.idmon)
                                            {
                                                chiTietDatMons.get(k).setSoluong(chiTietDatMons.get(k).soluong+chiTietDatMon.soluong);
                                                break;
                                            }
                                            else
                                                dem++;
                                        }
                                        if(dem==chiTietDatMons.size())
                                            chiTietDatMons.add(chiTietDatMon);
                                    }




                                    Toast.makeText(DatMonActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                                else
                                {
                                    Toast.makeText(DatMonActivity.this,"Bạn nhập số lượng không hợp lệ",Toast.LENGTH_SHORT).show();

                                }
                            }
                            catch (Exception ex)
                            {
                                Toast.makeText(DatMonActivity.this,"Bạn nhập số lượng không hợp lệ",Toast.LENGTH_SHORT).show();

                            }
                        }

                    }
                });
            }
        });
    }
    private void hienThiMon()
    {

            Cursor cursor = dataBase.GetData("SELECT * FROM Mon");
            lstMon = new ArrayList<>();
            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                String tenmon = cursor.getString(1);
                float giaban = cursor.getFloat(2);
                int idloai = cursor.getInt(3);
                Mon mon = new Mon(id, idloai, tenmon, giaban);
                lstMon.add(mon);
            }


        daMon = new AdapterMon(DatMonActivity.this,R.layout.hienthimon,lstMon);
        lvMon.setAdapter(daMon);

    }
    private void hienThiMonTheoLoai(int idLoaiMon)
    {

        Cursor cursor = dataBase.GetData("SELECT * FROM Mon where IDLoaiMon="+idLoaiMon+"");
        lstMon = new ArrayList<>();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenmon = cursor.getString(1);
            float giaban = cursor.getFloat(2);
            int idloai = cursor.getInt(3);
            Mon mon = new Mon(id, idloai, tenmon, giaban);
            lstMon.add(mon);
        }


        daMon = new AdapterMon(DatMonActivity.this,R.layout.hienthimon,lstMon);
        lvMon.setAdapter(daMon);

    }
    private  void hienThi()
    {
        Cursor cursor = dataBase.GetData("SELECT * FROM LoaiMon");
        lstLoaiMon= new ArrayList<>();
        while (cursor.moveToNext())
        {
            int id=cursor.getInt(0);
            String tenmon=cursor.getString(1);
            LoaiMon loaiMon = new LoaiMon(id,tenmon);
            lstLoaiMon.add(loaiMon);
        }
        da = new AdapterLoaiMonDatMon(DatMonActivity.this,R.layout.loaimonorder,lstLoaiMon);
        lvLoaiMon.setAdapter(da);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnudatmon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
     //  Toast.makeText(DatMonActivity.this,chiTietDatMons.size()+"",Toast.LENGTH_SHORT).show();
            bundle.putSerializable("chitiet", chiTietDatMons);
            Intent intent = new Intent(DatMonActivity.this, ChiTietDatMonActivity.class);
            intent.putExtra("data", bundle);
            startActivity(intent);
            finish();
        return super.onOptionsItemSelected(item);
    }
}