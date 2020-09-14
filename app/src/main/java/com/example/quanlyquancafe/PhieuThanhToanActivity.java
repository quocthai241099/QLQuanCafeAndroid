package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieuThanhToanActivity extends AppCompatActivity {
    int idhoadon;
    TextView tvMaHD, tvBan, tvNgay, tvThanhTien;
    Button btnThanhToan;
    DataBase dataBase;
    ArrayList<PhieuThanhToan> arrayList;
    AdapterPhieuThanhToan da;
    ListView lvPTT;
    float tongtien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_thanh_toan);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        Bundle kq = getIntent().getBundleExtra("data");
        idhoadon=(int)kq.getSerializable("idhoadon");
        btnThanhToan=findViewById(R.id.btnXacNhanThanhToan);
        tvBan=findViewById(R.id.tvBan);
        tvMaHD=findViewById(R.id.tvMaHD);
        tvNgay=findViewById(R.id.tvNgay);
        tvThanhTien=findViewById(R.id.tvTongTien);
        tongtien=0;
        lvPTT=findViewById(R.id.lvPhieuThanhToan);

        hienThi();
        xuLySuKien();
    }
    private void hienThi()
    {
        Cursor hoadon= dataBase.GetData("SELECT * FROM HoaDon where ID="+idhoadon+"");
        hoadon.moveToFirst();
        tvMaHD.setText("Mã HD: "+hoadon.getInt(0));
        tvBan.setText("Bàn: "+hoadon.getInt(2));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date= new Date(hoadon.getString(1));
        String ngay =format.format(date);
        tvNgay.setText("Ngày: "+ngay);
        arrayList = new ArrayList<>();
        Cursor chitiethd = dataBase.GetData("SELECT Mon.TenMon, ChiTietHD.SoLuong, Mon.GiaBan, ChiTietHD.ThanhTien from Mon, ChiTietHD, HoaDon where Mon.ID=ChiTietHD.IDMon and ChiTietHD.IDHoaDon=HoaDon.ID and ChiTietHD.IDHoaDon="+idhoadon+"");
        while (chitiethd.moveToNext())
        {
            String tenmon=chitiethd.getString(0);
            int soluong=chitiethd.getInt(1);
            float giaban=chitiethd.getFloat(2);
            float thanhtien=chitiethd.getFloat(3);
            PhieuThanhToan ptt = new PhieuThanhToan(tenmon,soluong,giaban,thanhtien);
            arrayList.add(ptt);
        }
       da = new AdapterPhieuThanhToan(PhieuThanhToanActivity.this,R.layout.phieuthanhtoan,arrayList);
       lvPTT.setAdapter(da);

       for (int i=0;i<arrayList.size();i++)
       {
           tongtien=tongtien+arrayList.get(i).thanhtien;
       }
       tvThanhTien.setText(tongtien+" VNĐ");
    }
    private void xuLySuKien()
    {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBase.QueryData("UPDATE HoaDon set TongTien="+tongtien+" where ID="+idhoadon+"");
                Cursor cursor = dataBase.GetData("SELECT * FROM HoaDon where ID="+idhoadon+"");
                cursor.moveToNext();
                int idban=cursor.getInt(2);
                dataBase.QueryData("UPDATE Ban set TrangThai='Trống' where ID="+idban+"");
                Toast.makeText(PhieuThanhToanActivity.this,"Thanh toán thành công",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}