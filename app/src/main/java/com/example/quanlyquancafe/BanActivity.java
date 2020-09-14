package com.example.quanlyquancafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BanActivity extends AppCompatActivity {
    DataBase dataBase;
    ArrayList<Ban> lstBan;
    GridView grvBan;
    AdapterBan adapterBan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        hienThi();
        xuLySuKien();
    }
    private void xuLySuKien()
    {
        grvBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialogQuanLyBan = new Dialog(BanActivity.this);
                dialogQuanLyBan.setContentView(R.layout.dialog_suaxoa);
                dialogQuanLyBan.setCanceledOnTouchOutside(false);
                dialogQuanLyBan.show();
                final int index=i;
                final TextView tvQuanLyBan;
                Button btnQuanLySuaBan, btnQuanLyXoaBan, btnHuyQuanLyBan;
                tvQuanLyBan=dialogQuanLyBan.findViewById(R.id.tvQuanLyLoaiMon);
                btnQuanLySuaBan=dialogQuanLyBan.findViewById(R.id.btnSuaLoaiMon);
                btnQuanLyXoaBan=dialogQuanLyBan.findViewById(R.id.btnXoaLoaiMon);
                btnHuyQuanLyBan=dialogQuanLyBan.findViewById(R.id.btnHuySuaXoaLoaiMon);
                btnQuanLySuaBan.setText("Sửa tên bàn");
                btnQuanLyXoaBan.setText("Xóa bàn");
                tvQuanLyBan.setText("Quản lý bàn");
                btnHuyQuanLyBan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogQuanLyBan.dismiss();
                    }
                });
                btnQuanLySuaBan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialogSuaBan = new Dialog(BanActivity.this);
                        dialogSuaBan.setContentView(R.layout.dialog_sualoaimon);
                        dialogSuaBan.setCanceledOnTouchOutside(false);
                        dialogSuaBan.show();
                        TextView tvSuaTenBan;
                        final EditText edtTenBanSua;
                        Button btnSuaTenBan, btnHuySuaTenBan;
                        tvSuaTenBan=dialogSuaBan.findViewById(R.id.tieuDeSuaLoaiMon);
                        edtTenBanSua=dialogSuaBan.findViewById(R.id.edtTenLoaiMonSua);
                        btnSuaTenBan=dialogSuaBan.findViewById(R.id.btnLuuSuaLoaiMon);
                        btnHuySuaTenBan=dialogSuaBan.findViewById(R.id.btnHuySuaLoaiMon);
                        tvSuaTenBan.setText("Thay đổi tên bàn");
                        edtTenBanSua.setHint("Tên bàn");
                        btnHuySuaTenBan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogSuaBan.dismiss();
                            }
                        });
                        edtTenBanSua.setText(lstBan.get(index).tenban);
                        btnSuaTenBan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(TextUtils.isEmpty(edtTenBanSua.getText().toString()))
                                {
                                    Toast.makeText(BanActivity.this,"Bạn chưa nhập tên bàn",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    dataBase.QueryData("UPDATE Ban set TenBan='"+edtTenBanSua.getText().toString()+"' where ID="+lstBan.get(index).id+"");
                                    hienThi();
                                    dialogQuanLyBan.dismiss();
                                    dialogSuaBan.dismiss();
                                }
                            }
                        });


                    }
                });
                btnQuanLyXoaBan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialogXoaBan = new Dialog(BanActivity.this);
                        dialogXoaBan.setCanceledOnTouchOutside(false);
                        dialogXoaBan.setContentView(R.layout.dialog_xoaloaimon);
                        dialogXoaBan.show();
                        TextView tvXoaBan, tvTenBanXoa;
                        Button btnXoaTenBan, btnHuyXoaTenBan;
                        tvXoaBan = dialogXoaBan.findViewById(R.id.tieuDeXoaLoaiMon);
                        tvTenBanXoa = dialogXoaBan.findViewById(R.id.tvTenLoaiMonXoa);
                        btnXoaTenBan = dialogXoaBan.findViewById(R.id.btnLuuXoaLoaiMon);
                        btnHuyXoaTenBan = dialogXoaBan.findViewById(R.id.btnHuyXoaLoaiMon);
                        if (lstBan.get(index).getTrangthai().toString().equals("Có người")) {
                            tvXoaBan.setText("Xóa bàn");
                            tvTenBanXoa.setText("Bạn cần phải thanh toán bàn này trước");
                            btnXoaTenBan.setVisibility(View.INVISIBLE);
                            btnHuyXoaTenBan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogQuanLyBan.dismiss();
                                    dialogXoaBan.dismiss();
                                }
                            });
                        }
                        else
                        {

                        tvXoaBan.setText("Xóa bàn");
                        tvTenBanXoa.setText(lstBan.get(index).tenban);
                        btnHuyXoaTenBan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogXoaBan.dismiss();
                            }
                        });
                        btnXoaTenBan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final Dialog dialogXacNhanXoa = new Dialog(BanActivity.this);
                                dialogXacNhanXoa.setContentView(R.layout.dialog_xacnhanxoa);
                                dialogXacNhanXoa.setCanceledOnTouchOutside(false);
                                dialogXacNhanXoa.show();
                                TextView tvXacNhanXoa;
                                Button btnCo, btnKhong;
                                btnCo = dialogXacNhanXoa.findViewById(R.id.btnXacNhanXoa);
                                btnKhong = dialogXacNhanXoa.findViewById(R.id.btnHuyXacNhanXoa);
                                tvXacNhanXoa = dialogXacNhanXoa.findViewById(R.id.tvXacNhanXoa);
                                tvXacNhanXoa.setText("Bạn có muốn xóa bàn " + lstBan.get(index).tenban + " không?");
                                btnCo.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialogQuanLyBan.dismiss();
                                        dialogXacNhanXoa.dismiss();
                                        dialogXoaBan.dismiss();
                                        dataBase.QueryData("DELETE FROM Ban where ID=" + lstBan.get(index).id + "");
                                        hienThi();
                                    }
                                });
                                btnKhong.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialogQuanLyBan.dismiss();
                                        dialogXacNhanXoa.dismiss();
                                        dialogXoaBan.dismiss();
                                    }
                                });
                            }
                        });
                    }
                    }
                });
            }
        });
    }
    private void hienThi()
    {
        grvBan=(GridView)findViewById(R.id.grvBan);
        Cursor cursor = dataBase.GetData("SELECT * from Ban");
        lstBan = new ArrayList<>();
        while(cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenban=cursor.getString(1);
            String trangthai=cursor.getString(2);
            Ban ban = new Ban(id,tenban,trangthai);
            lstBan.add(ban);
        }

        adapterBan = new AdapterBan(BanActivity.this,R.layout.hienthiban,lstBan);
        grvBan.setAdapter(adapterBan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnu_tem,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dialogThemBan();
        return super.onOptionsItemSelected(item);
    }

    private void dialogThemBan()
    {
        final Dialog dialog = new Dialog(BanActivity.this);
        dialog.setContentView(R.layout.dialog_themloaimon);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        TextView tvThemBan;
        final EditText edtTenBan;
        Button btnThemBan, btnHuyThemBan;
        tvThemBan=dialog.findViewById(R.id.tvTieuDeThemLoaiMon);
        edtTenBan=dialog.findViewById(R.id.edtTenLoai);
        btnThemBan=dialog.findViewById(R.id.btnThemLoaiMon);
        btnHuyThemBan=dialog.findViewById(R.id.btnHuyLoaiMon);
        tvThemBan.setText("Thêm bàn mới");
        edtTenBan.setHint("Tên bàn");
        btnHuyThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtTenBan.getText().toString()))
                {
                    Toast.makeText(BanActivity.this,"Bạn chưa nhập thông tin bàn",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dataBase.QueryData("INSERT INTO Ban values(null,'"+edtTenBan.getText().toString()+"','Trống')");
                    hienThi();
                    dialog.dismiss();
                }
            }
        });
    }
}