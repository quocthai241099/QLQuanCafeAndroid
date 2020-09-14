package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SuaMonActivity extends AppCompatActivity {
    EditText edtTenMonSua, edtGiaBanSua;
    TextView tvNhomMonSua;
    Button btnSua;
    Mon mon;
    LoaiMon loaiMon;
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_mon);
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);
        edtGiaBanSua=findViewById(R.id.edtGiaBanSua);
        edtTenMonSua=findViewById(R.id.edtTenMonSua);
        tvNhomMonSua=findViewById(R.id.tvNhomMonSua);
        btnSua=findViewById(R.id.btnSuaMon);
        Bundle kq= getIntent().getBundleExtra("data");
        mon=(Mon) kq.getSerializable("mon");
        edtTenMonSua.setText(mon.getTenmon());
        edtGiaBanSua.setText(mon.getGiaban()+"");;
        Bundle lm = getIntent().getBundleExtra("dataloaimon");
        loaiMon=(LoaiMon)lm.getSerializable("loaimon");
        tvNhomMonSua.setText("Nhóm món\n"+loaiMon.getTenloai());
        xuLySuKien();
    }
    private void xuLySuKien()
    {
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtGiaBanSua.getText().toString())==false&&TextUtils.isEmpty(edtTenMonSua.getText().toString())==false)
                {
                    try {
                        float giaban=Float.parseFloat(edtGiaBanSua.getText().toString());
                        if(giaban<0)
                        {
                            Toast.makeText(SuaMonActivity.this,"Bạn nhập thông tin không hợp lệ",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            dataBase.QueryData("UPDATE Mon SET TenMon='" + edtTenMonSua.getText().toString() + "',GiaBan=" + giaban + " where ID=" + mon.getId() + "");
                            LoaiMon lm = new LoaiMon(loaiMon.getId(),loaiMon.getTenloai());
                            Intent intent = new Intent();
                            Bundle bd = new Bundle();
                            bd.putSerializable("loaimon",loaiMon);
                            intent.putExtra("data",bd);
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(SuaMonActivity.this,"Bạn nhập thông tin không hợp lệ",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(SuaMonActivity.this,"Bạn chưa nhập thông tin cần sửa",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}