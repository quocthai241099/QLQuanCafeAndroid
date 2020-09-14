package com.example.quanlyquancafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterHoaDon extends ArrayAdapter<HoaDon> {
    ArrayList<HoaDon> lstHoaDon;
    Context context;
    int layoutID;
    public AdapterHoaDon(Context context, int resource, ArrayList<HoaDon> hoaDons)
    {
        super(context,resource,hoaDons);
        this.context=context;
        layoutID=resource;
        lstHoaDon=hoaDons;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(this.layoutID,parent,false);
        }
        TextView id, ban, ngaylap,tongtien;
        id=convertView.findViewById(R.id.tvIDHoaDonHT);
        ban=convertView.findViewById(R.id.tvIDBanHT);
        ngaylap=convertView.findViewById(R.id.tvNgayLapHT);
        tongtien=convertView.findViewById(R.id.tvTongTienHT);
        HoaDon hd = lstHoaDon.get(position);
        id.setText(hd.getId()+"");
        ban.setText(hd.getIdban()+"");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date= new Date(hd.getThoigian().toString());
        String ngay =format.format(date);
        ngaylap.setText(ngay);
        tongtien.setText(hd.getTongtien()+"");
        return convertView;
    }

    @Override
    public int getPosition(@Nullable HoaDon item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
