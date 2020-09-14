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

public class AdapterMon extends ArrayAdapter<Mon> {
    ArrayList<Mon> lstMon;
    Context context;
    int layoutID;
    public AdapterMon( Context context, int resource, ArrayList<Mon> mons) {
        super(context, resource, mons);
        this.context=context;
        layoutID=resource;
        lstMon=mons;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(this.layoutID, parent, false);
        }
            TextView tenmon, giaban;
            tenmon=convertView.findViewById(R.id.tvTenMon);
            giaban=convertView.findViewById(R.id.tvGia);

            Mon mon = lstMon.get(position);
            tenmon.setText(mon.getTenmon());
            giaban.setText(mon.getGiaban()+"");


        return convertView;
    }

    @Override
    public int getPosition(@Nullable Mon item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Mon getItem(int position) {
        return super.getItem(position);
    }
}
