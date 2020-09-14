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

public class AdapterPhieuThanhToan extends ArrayAdapter<PhieuThanhToan> {
    ArrayList<PhieuThanhToan> lstPhieuThanhToan;
    Context context;
    int layoutID;
    public AdapterPhieuThanhToan(Context context, int resource, ArrayList<PhieuThanhToan> pts)
    {
        super(context,resource,pts);
        this.context=context;
        layoutID=resource;
        lstPhieuThanhToan=pts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(this.layoutID,parent,false);
        }
        TextView tvTenMon, tvSoLuong, tvDonGia, tvThanhTien;
        tvTenMon=convertView.findViewById(R.id.tvTenMonHTPTT);
        tvSoLuong=convertView.findViewById(R.id.tvSoLuongHTPTT);
        tvDonGia=convertView.findViewById(R.id.tvDonGiaHTPTT);
        tvThanhTien=convertView.findViewById(R.id.tvThanhTienHTPTT);
        PhieuThanhToan phieuThanhToan = lstPhieuThanhToan.get(position);
        tvTenMon.setText(phieuThanhToan.getTenmon());
        tvSoLuong.setText(phieuThanhToan.getSoluong()+"");
        tvDonGia.setText(phieuThanhToan.getDongia()+"");
        tvThanhTien.setText(phieuThanhToan.getThanhtien()+"");
        return convertView;
    }

    @Override
    public int getPosition(@Nullable PhieuThanhToan item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
