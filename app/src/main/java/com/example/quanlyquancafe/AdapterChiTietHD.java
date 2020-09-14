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

public class AdapterChiTietHD extends ArrayAdapter<ChiTietHD> {
    ArrayList<ChiTietHD> lstCT;
    Context context;
    int layoutID;
    public AdapterChiTietHD(Context context,int resource, ArrayList<ChiTietHD> chiTietHDS)
    {
        super(context,resource,chiTietHDS);
        this.context=context;
        layoutID=resource;
        lstCT=chiTietHDS;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(this.layoutID,parent,false);

        }
        TextView idhd, idmon, soluong, thanhtien;
        idhd=convertView.findViewById(R.id.tvIDHoaDonCTHT);
        idmon=convertView.findViewById(R.id.tvIDMonCTHT);
        soluong=convertView.findViewById(R.id.tvSoLuongCTHT);
        thanhtien=convertView.findViewById(R.id.tvThanhTienHTCT);
        ChiTietHD chiTietHD = lstCT.get(position);
        idhd.setText(chiTietHD.getIdhoadon()+"");
        idmon.setText(chiTietHD.getIdmon()+"");
        soluong.setText(chiTietHD.getSoluong()+"");
        thanhtien.setText(chiTietHD.getThanhtien()+"");
        return convertView;
    }

    @Override
    public int getPosition(@Nullable ChiTietHD item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
