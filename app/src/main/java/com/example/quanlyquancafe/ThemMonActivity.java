package com.example.quanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ThemMonActivity extends AppCompatActivity {
    Button btnThem;
    EditText edtTenMon, edtGiaBan;
    TextView nhomMon;
    LoaiMon loaiMon;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon);
        btnThem=findViewById(R.id.btnThemMon);
        edtGiaBan=findViewById(R.id.edtGiaBanThem);
        edtTenMon=findViewById(R.id.edtTenMonThem);
        nhomMon=findViewById(R.id.tvNhomMon);
        Bundle kq = getIntent().getBundleExtra("data");
        loaiMon=(LoaiMon)kq.getSerializable("loaimon");
        nhomMon.setText("Nhóm món\n"+loaiMon.getTenloai());
        dataBase = new DataBase(this,"QuanLyQuanCafe.sqlite",null,1);

        xuLySuKien();
    }
    private  void xuLySuKien()
    {
       btnThem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                   if(TextUtils.isEmpty(edtTenMon.getText().toString())||TextUtils.isEmpty(edtGiaBan.getText().toString()))
                   {
                       Toast.makeText(ThemMonActivity.this,"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                      try {
                          float giaban=Float.parseFloat(edtGiaBan.getText().toString());
                          if(giaban<0)
                          {
                              Toast.makeText(ThemMonActivity.this,"Bạn nhập thông tin không hợp lệ",Toast.LENGTH_SHORT).show();
                          }
                          else
                          {
                             dataBase.QueryData("INSERT INTO Mon values(null,'"+edtTenMon.getText().toString()+"',"+giaban+","+loaiMon.getId()+")");
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
                          Toast.makeText(ThemMonActivity.this,"Bạn nhập thông tin không hợp lệ",Toast.LENGTH_SHORT).show();

                      }
                   }

           }
       });
    }
}