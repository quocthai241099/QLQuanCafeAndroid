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

public class AdapterChiTietDatMon extends ArrayAdapter<ChiTietDatMon> {
    ArrayList<ChiTietDatMon> lstChiTiet;
    Context context;
    int layoutID;
    public AdapterChiTietDatMon(Context context, int resource, ArrayList<ChiTietDatMon> lstChiTiet)
    {
        super(context,resource,lstChiTiet);
        this.context=context;
        layoutID=resource;
        this.lstChiTiet=lstChiTiet;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(this.layoutID,parent,false);
        }
        TextView tenmon, soluong,thanhtien;
        tenmon=convertView.findViewById(R.id.tvTenMonChiTiet);
        soluong=convertView.findViewById(R.id.tvSoLuongChiTiet);
        thanhtien=convertView.findViewById(R.id.tvThanhTienChiTiet);
        ChiTietDatMon ct = lstChiTiet.get(position);
        tenmon.setText(ct.getTenmon());
        soluong.setText(ct.getSoluong()+"");
        thanhtien.setText(ct.getThanhtien()+"");
        return convertView;
    }

    @Nullable
    @Override
    public ChiTietDatMon getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable ChiTietDatMon item) {
        return super.getPosition(item);
    }
}
