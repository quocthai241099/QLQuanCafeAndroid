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

public class AdapterLoaiMonDatMon extends ArrayAdapter<LoaiMon> {
    ArrayList<LoaiMon> lstLoaiMon;
    Context context;
    int layoutID;
    public AdapterLoaiMonDatMon(Context context, int resource, ArrayList<LoaiMon> loaiMons)
    {
        super(context,resource,loaiMons);
        this.context=context;
        layoutID=resource;
        lstLoaiMon=loaiMons;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(this.layoutID,parent,false);
        }
        TextView tvLoaiMon;

        tvLoaiMon=convertView.findViewById(R.id.tvLoaiMonDatMon);
        LoaiMon loaiMon = lstLoaiMon.get(position);
        tvLoaiMon.setText(loaiMon.getTenloai());

        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(@Nullable LoaiMon item) {
        return super.getPosition(item);
    }
}
