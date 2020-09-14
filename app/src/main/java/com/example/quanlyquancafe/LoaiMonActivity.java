package com.example.quanlyquancafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class LoaiMonActivity extends AppCompatActivity {
    DataBase dataBase;
    ArrayList<LoaiMon>lstLoaiMon;
    ListView lvLoaiMon;
    AdapterLoaiMon da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_mon);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        lvLoaiMon=findViewById(R.id.lvLoaiMon);
        hienThi();
        xuLuSuKien();

    }
    private void xuLuSuKien()
    {
        lvLoaiMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id=lstLoaiMon.get(i).id;
                String tenmon=lstLoaiMon.get(i).tenloai;
                LoaiMon loaiMon = new LoaiMon(id,tenmon);
                Intent intent = new Intent(LoaiMonActivity.this,MonActivity.class);
                Bundle bd = new Bundle();
                bd.putSerializable("loaimon",loaiMon);
                intent.putExtra("data",bd);
                startActivity(intent);
            }
        });
        lvLoaiMon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialogSuaXoa = new Dialog(LoaiMonActivity.this);
                dialogSuaXoa.setContentView(R.layout.dialog_suaxoa);
                dialogSuaXoa.show();
                dialogSuaXoa.setCanceledOnTouchOutside(false);
                final Button btnSua, btnXoa, btnHuy;
                final int index=i;
                btnSua=dialogSuaXoa.findViewById(R.id.btnSuaLoaiMon);
                btnXoa=dialogSuaXoa.findViewById(R.id.btnXoaLoaiMon);
                btnHuy=dialogSuaXoa.findViewById(R.id.btnHuySuaXoaLoaiMon);
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogSuaXoa.dismiss();
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialogSua = new Dialog(LoaiMonActivity.this);
                        dialogSua.setContentView(R.layout.dialog_sualoaimon);
                        dialogSua.show();
                        dialogSua.setCanceledOnTouchOutside(false);
                        Button btnHuySua, btnLuuSua;
                        final EditText edtTenLoaiMon = dialogSua.findViewById(R.id.edtTenLoaiMonSua);
                        edtTenLoaiMon.setText(lstLoaiMon.get(index).getTenloai());
                        btnLuuSua=dialogSua.findViewById(R.id.btnLuuSuaLoaiMon);
                        btnHuySua=dialogSua.findViewById(R.id.btnHuySuaLoaiMon);
                        btnHuySua.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogSua.dismiss();
                            }
                        });
                        btnLuuSua.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(TextUtils.isEmpty(edtTenLoaiMon.getText().toString()))
                                {
                                    Toast.makeText(LoaiMonActivity.this,"Bạn chưa nhập thông tin cần sửa",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    dataBase.QueryData("UPDATE LoaiMon Set TenLoai='" + edtTenLoaiMon.getText().toString() + "' where ID=" + lstLoaiMon.get(index).getId() + "");
                                    dialogSua.dismiss();
                                    dialogSuaXoa.dismiss();
                                    hienThi();
                                }
                            }
                        });

                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialogXoa = new Dialog(LoaiMonActivity.this);
                        dialogXoa.setContentView(R.layout.dialog_xoaloaimon);
                        dialogXoa.setCanceledOnTouchOutside(false);
                        dialogXoa.show();
                        Button btnXoaLoaiMon, btnHuyXoaLoaiMon;
                        TextView tvTenLoaiMonXoa;
                        btnXoaLoaiMon=dialogXoa.findViewById(R.id.btnLuuXoaLoaiMon);
                        btnHuyXoaLoaiMon=dialogXoa.findViewById(R.id.btnHuyXoaLoaiMon);
                        tvTenLoaiMonXoa=dialogXoa.findViewById(R.id.tvTenLoaiMonXoa);
                        tvTenLoaiMonXoa.setText(lstLoaiMon.get(index).tenloai);
                        Cursor cursor=dataBase.GetData("SELECT * FROM Mon where IDLoaiMon="+lstLoaiMon.get(index).id+"");
                        if(cursor.getCount()>0)
                        {
                            tvTenLoaiMonXoa.setText("Bạn cần phải xóa các món trong nhóm này trước");
                            btnXoaLoaiMon.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            btnXoaLoaiMon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final Dialog dialogXacNhanXoa = new Dialog(LoaiMonActivity.this);
                                    dialogXacNhanXoa.setCanceledOnTouchOutside(false);
                                    dialogXacNhanXoa.setContentView(R.layout.dialog_xacnhanxoa);
                                    dialogXacNhanXoa.show();
                                    final TextView tvXacNhanXoa = dialogXacNhanXoa.findViewById(R.id.tvXacNhanXoa);
                                    Button btnXacNhanXoa=dialogXacNhanXoa.findViewById(R.id.btnXacNhanXoa);
                                    Button btnHuyXacNhanXoa=dialogXacNhanXoa.findViewById(R.id.btnHuyXacNhanXoa);
                                    tvXacNhanXoa.setText("Bạn có muốn xóa loại món "+lstLoaiMon.get(index).tenloai+"?");
                                    btnHuyXacNhanXoa.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialogSuaXoa.dismiss();
                                            dialogXacNhanXoa.dismiss();
                                            dialogXoa.dismiss();
                                        }
                                    });
                                    btnXacNhanXoa.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                           dataBase.QueryData("DELETE FROM LoaiMon where ID="+lstLoaiMon.get(index).id+"");
                                            dialogSuaXoa.dismiss();
                                            dialogXacNhanXoa.dismiss();
                                            dialogXoa.dismiss();
                                            hienThi();
                                        }
                                    });
                                }
                            });

                        }
                        btnHuyXoaLoaiMon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogSuaXoa.dismiss();
                                dialogXoa.dismiss();
                            }
                        });

                    }
                });
                return true;
            }
        });
    }

    private void hienThi()
    {
        Cursor dsLoaiMon = dataBase.GetData("SELECT * FROM LoaiMon");
        lstLoaiMon = new ArrayList<>();
        while (dsLoaiMon.moveToNext())
        {
            int id=dsLoaiMon.getInt(0);
            String tenloai=dsLoaiMon.getString(1);
            LoaiMon loaiMon = new LoaiMon(id,tenloai);
            lstLoaiMon.add(loaiMon);
        }
        da = new AdapterLoaiMon(LoaiMonActivity.this,R.layout.hienthiloaimon,lstLoaiMon);
        lvLoaiMon.setAdapter(da);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnu_tem,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dialogThem();

        return super.onOptionsItemSelected(item);
    }

    private void dialogThem()
    {
        final Dialog dialog = new Dialog(LoaiMonActivity.this);
        dialog.setContentView(R.layout.dialog_themloaimon);
        dialog.show();
        final EditText edtTenLoai = dialog.findViewById(R.id.edtTenLoai);
        Button btnThem = dialog.findViewById(R.id.btnThemLoaiMon);
        Button btnHuy=dialog.findViewById(R.id.btnHuyLoaiMon);
        dialog.setCanceledOnTouchOutside(false);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtTenLoai.getText().toString()))
                    Toast.makeText(LoaiMonActivity.this,"Bạn chưa nhập thông tin cần thêm",Toast.LENGTH_SHORT).show();
                else {
                     dataBase.QueryData("INSERT INTO LoaiMon values(null,'"+edtTenLoai.getText().toString()+"')");
                    dialog.dismiss();
                    hienThi();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}