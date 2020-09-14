package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TamTinhActivity extends AppCompatActivity {
    ListView lvTamTinh;
    Button btnThanhToan;
    DataBase dataBase;
    AdapterChiTietDatMon da;
    ArrayList<ChiTietDatMon> arrayList;

    Ban ban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tam_tinh);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        lvTamTinh=findViewById(R.id.lvTamTinh);
        btnThanhToan=findViewById(R.id.btnThanhToan);
        Bundle kq = getIntent().getBundleExtra("data");
        ban=(Ban)kq.getSerializable("ban");
        hienThi();
        xuLySuKien();
    }
    private  void xuLySuKien()
    {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(TamTinhActivity.this, PhieuThanhToanActivity.class);
                    Cursor cursor = dataBase.GetData("SELECT * FROM HoaDon where IDBan=" + ban.id + "");
                    cursor.moveToLast();
                    int idhd = cursor.getInt(0);
                    Bundle bd = new Bundle();
                    bd.putInt("idhoadon", idhd);
                    intent.putExtra("data", bd);
                    startActivity(intent);
                    finish();

            }
        });
        lvTamTinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialog = new Dialog(TamTinhActivity.this);
                dialog.setContentView(R.layout.dialog_suaxoa);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                TextView tvQuanLy = dialog.findViewById(R.id.tvQuanLyLoaiMon);
                Button btnSua = dialog.findViewById(R.id.btnSuaLoaiMon);
                Button btnXoa= dialog.findViewById(R.id.btnXoaLoaiMon);
                tvQuanLy.setVisibility(View.INVISIBLE);
                btnSua.setText("Thay đổi số lượng");
                btnXoa.setText("Xóa");
                Button btnHuy = dialog.findViewById(R.id.btnHuySuaXoaLoaiMon);
                final int index=i;
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dataBase.QueryData("DELETE from ChiTietHD  where IDMon="+arrayList.get(index).idmon+"");
                        hienThi();
                        dialog.dismiss();
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialogSua = new Dialog(TamTinhActivity.this);
                        dialog.dismiss();
                        dialogSua.setCanceledOnTouchOutside(false);
                        dialogSua.setContentView(R.layout.dialog_sualoaimon);
                        dialogSua.show();
                        TextView tv =dialogSua.findViewById(R.id.tieuDeSuaLoaiMon);
                        tv.setText("Cập nhật số lượng");
                        Button btnCapNhat = dialogSua.findViewById(R.id.btnLuuSuaLoaiMon);
                        Button btnHuy = dialogSua.findViewById(R.id.btnHuySuaLoaiMon);
                        final EditText edt=dialogSua.findViewById(R.id.edtTenLoaiMonSua);
                        edt.setHint("Số lượng");
                        btnCapNhat.setText("Cập nhật");
                        btnHuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogSua.dismiss();
                            }
                        });
                        btnCapNhat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    int soluong=Integer.parseInt(edt.getText().toString());
                                    if(soluong>0)
                                    {
                                       Cursor cursor = dataBase.GetData("SELECT * FROM Mon where ID="+arrayList.get(index).idmon+"");
                                       cursor.moveToNext();
                                       float giaban=cursor.getFloat(2);

                                      float thanhtien=soluong*cursor.getFloat(2);
                                       dataBase.QueryData("UPDATE ChiTietHD set SoLuong="+soluong+", ThanhTien="+thanhtien+" where IDMon="+arrayList.get(index).idmon+"");
                                        dialogSua.dismiss();
                                        hienThi();
                                    }
                                    else
                                    {
                                        Toast.makeText(TamTinhActivity.this,"Số lượng phải lớn hơn 0",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception ex)
                                {
                                    Toast.makeText(TamTinhActivity.this,"Nhập số lượng không hợp lệ",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
    }
    private void hienThi()
    {
        arrayList = new ArrayList<>();
        Cursor cursor = dataBase.GetData("SELECT * FROM HoaDon where IDBan="+ban.id+"");
        cursor.moveToLast();
        int idhd = cursor.getInt(0);
    Cursor chiTietHD = dataBase.GetData("SELECT ChiTietHD.IDMon, Mon.TenMon, ChiTietHD.SoLuong,ChiTietHD.ThanhTien from Mon, ChiTietHD, HoaDon where Mon.ID=ChiTietHD.IDMon and ChiTietHD.IDHoaDon=HoaDon.ID and ChiTietHD.IDHoaDon="+idhd+" ");
      //  Cursor chiTietHD=dataBase.GetData("SELECT * FROM Mon, ChiTietHD, HoaDon where Mon.ID=ChiTietHD.IDMon and ChiTietHD.IDHoaDon=HoaDon.ID and HoaDon.ID=1");
       while (chiTietHD.moveToNext())
        {
          int idmon=chiTietHD.getInt(0);
            String tenmon=chiTietHD.getString(1);
         int soluong=chiTietHD.getInt(2);
          float thanhtien=chiTietHD.getFloat(3);
            ChiTietDatMon chiTietDatMon = new ChiTietDatMon(tenmon,soluong,thanhtien,idmon,ban.id   );
         arrayList.add(chiTietDatMon);
        }
        da = new AdapterChiTietDatMon(TamTinhActivity.this,R.layout.hienthichitietdatmon,arrayList);
        lvTamTinh.setAdapter(da);
    }
}