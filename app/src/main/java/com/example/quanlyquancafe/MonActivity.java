package com.example.quanlyquancafe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MonActivity extends AppCompatActivity {
    LoaiMon loaiMon;
    int idloaimon;
    TextView tvTenLoaiMon;
    DataBase dataBase;
    ArrayList<Mon> lstMon;
    ListView lvMon;
    AdapterMon da;
    int REQUEST_CODE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon);
        tvTenLoaiMon=findViewById(R.id.tvTenLoai);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        Bundle kq= getIntent().getBundleExtra("data");
        loaiMon=(LoaiMon)kq.getSerializable("loaimon");
        idloaimon=loaiMon.getId();
        tvTenLoaiMon.setText(loaiMon.getTenloai());
        lvMon=findViewById(R.id.lvMon);
        hienThi();
        xuLySuKien();
    }
    private void xuLySuKien()
    {
        lvMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final Dialog dialogQuanLyMon = new Dialog(MonActivity.this);
                dialogQuanLyMon.setContentView(R.layout.dialog_quanlymon);
                dialogQuanLyMon.setCanceledOnTouchOutside(false);
                dialogQuanLyMon.show();
                Button btnHuyQuanLyMon, btnSuaQuanLyMon, btnXoaQuanLyMon;
                btnHuyQuanLyMon=dialogQuanLyMon.findViewById(R.id.btnHuyQuanLyMon);
                btnSuaQuanLyMon=dialogQuanLyMon.findViewById(R.id.btnSuaThongTinMon);
                btnXoaQuanLyMon=dialogQuanLyMon.findViewById(R.id.btnXoaThongTinMon);
                final int index=i;
                btnHuyQuanLyMon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogQuanLyMon.dismiss();
                    }
                });
                btnSuaQuanLyMon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Mon mon = new Mon(lstMon.get(index).id,lstMon.get(index).idloaimon,lstMon.get(index).tenmon,lstMon.get(index).giaban);
                        LoaiMon lm = new LoaiMon(loaiMon.id,loaiMon.tenloai);
                        Intent intent = new Intent(MonActivity.this,SuaMonActivity.class);
                        Bundle bd = new Bundle();
                        bd.putSerializable("mon",mon);
                        intent.putExtra("data",bd);
                        bd.putSerializable("loaimon",lm);
                        intent.putExtra("dataloaimon",bd);
                        startActivityForResult(intent,REQUEST_CODE);
                        dialogQuanLyMon.dismiss();
                    }
                });
                btnXoaQuanLyMon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialogXoa = new Dialog(MonActivity.this);
                        dialogXoa.setContentView(R.layout.dialog_xoamon);
                        dialogXoa.setCanceledOnTouchOutside(false);
                        dialogXoa.show();
                        TextView tvTenMonXoa = dialogXoa.findViewById(R.id.tvtenMonXoa);
                        Button btnHuyXoa=dialogXoa.findViewById(R.id.btnHuyXoaMon);
                        Button btnXoaMon = dialogXoa.findViewById(R.id.btnXoaMon);
                        Cursor cursor = dataBase.GetData("SELECT * FROM ChiTietHD where IDMon="+lstMon.get(index).id+"");
                        if(cursor.getCount()>0)
                        {
                            tvTenMonXoa.setText("Bạn cần phải xóa những chi tiết hóa đơn của món này trước");
                            btnXoaMon.setVisibility(View.INVISIBLE);
                        }
                        else {
                            dialogQuanLyMon.dismiss();
                            dialogXoa.dismiss();
                            final Dialog dialogXacNhanXoa = new Dialog(MonActivity.this);
                            dialogXacNhanXoa.setContentView(R.layout.dialog_xacnhanxoa);
                            dialogXacNhanXoa.setCanceledOnTouchOutside(false);
                            dialogXacNhanXoa.show();
                            tvTenMonXoa.setText(lstMon.get(index).tenmon);
                            Button btnCo=dialogXacNhanXoa.findViewById(R.id.btnXacNhanXoa);
                            TextView tvXacNhan=dialogXacNhanXoa.findViewById(R.id.tvXacNhanXoa);
                            tvXacNhan.setText("Bạn có muốn xóa món "+lstMon.get(index).tenmon+" không?");
                            Button btnKhong=dialogXacNhanXoa.findViewById(R.id.btnHuyXacNhanXoa);
                            btnCo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dataBase.QueryData("DELETE FROM Mon where ID="+lstMon.get(index).id+"");
                                    hienThi();
                                    dialogQuanLyMon.dismiss();
                                    dialogXoa.dismiss();
                                    dialogXacNhanXoa.dismiss();
                                }
                            });
                            btnKhong.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogQuanLyMon.dismiss();
                                    dialogXacNhanXoa.dismiss();
                                    dialogXoa.dismiss();
                                }
                            });

                        }
                        btnHuyXoa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogQuanLyMon.dismiss();
                                dialogXoa.dismiss();
                            }
                        });
                    }
                });
            }
        });
    }
    private void hienThi()
    {
        Cursor dsMon = dataBase.GetData("SELECT * FROM Mon where IDLoaiMon="+idloaimon+"");
        lstMon = new ArrayList<>();
        while (dsMon.moveToNext())
        {
            int id=dsMon.getInt(0);
            String tenmon=dsMon.getString(1);
            float giaban=dsMon.getFloat(2);
            int idloai = dsMon.getInt(3);
            Mon mon = new Mon(id,idloaimon,tenmon,giaban);
            lstMon.add(mon);
        }
        da = new AdapterMon(MonActivity.this,R.layout.hienthimon,lstMon);
        lvMon.setAdapter(da);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnu_back,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


               themMon();


        return super.onOptionsItemSelected(item);
    }
    private void themMon()
    {
        LoaiMon lm = new LoaiMon(loaiMon.getId(),loaiMon.getTenloai());
        Intent intent = new Intent(MonActivity.this,ThemMonActivity.class);
        Bundle bd = new Bundle();
        bd.putSerializable("loaimon",lm);
        intent.putExtra("data",bd);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null) {
            Bundle kq= getIntent().getBundleExtra("data");
            loaiMon=(LoaiMon)kq.getSerializable("loaimon");
            idloaimon=loaiMon.getId();
            tvTenLoaiMon.setText(loaiMon.getTenloai());
            hienThi();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}