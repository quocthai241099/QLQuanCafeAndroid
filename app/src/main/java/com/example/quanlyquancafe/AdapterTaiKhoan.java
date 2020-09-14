package com.example.quanlyquancafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdapterTaiKhoan extends ArrayAdapter<TaiKhoan> {
    ArrayList<TaiKhoan> lstTaiKhoan;
    Context context;
    int layoutID;
    public AdapterTaiKhoan(Context context, int resource, ArrayList<TaiKhoan> taiKhoans) {
        super(context, resource, taiKhoans);
        this.context=context;
        layoutID=resource;
        lstTaiKhoan=taiKhoans;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(this.layoutID,parent,false);
        }
        TextView tvHoTen, tvUser, tvPhanQuyen;
        tvHoTen=convertView.findViewById(R.id.tvNguoiDungHoTen);
        tvUser=convertView.findViewById(R.id.tvNguoiDungUser);
        tvPhanQuyen=convertView.findViewById(R.id.tvNguoiDungPhanQuyen);
        TaiKhoan taiKhoan = lstTaiKhoan.get(position);
        tvHoTen.setText(taiKhoan.getHoten());
        tvUser.setText(taiKhoan.getUser());
        if(taiKhoan.getPhanquyen()==1)
            tvPhanQuyen.setText("Admin");
        else
            tvPhanQuyen.setText("Bán hàng");
        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(@Nullable TaiKhoan item) {
        return super.getPosition(item);
    }
}
