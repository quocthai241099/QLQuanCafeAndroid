package com.example.quanlyquancafe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterBan extends BaseAdapter {
    Context context;
    int layout;
    List<Ban> lstBan;

    public AdapterBan(Context context, int layout, List<Ban> lstBan) {
        this.context = context;
        this.layout = layout;
        this.lstBan = lstBan;
    }

    @Override
    public int getCount() {
        return lstBan.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder
    {
        TextView tvBan;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view ==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.tvBan=(TextView)view.findViewById(R.id.tvBan);
            view.setTag(holder);
        }
        else
        {
            holder=(ViewHolder) view.getTag();
        }
        Ban ban = lstBan.get(i);
        holder.tvBan.setText(ban.getTenban());
        if(lstBan.get(i).trangthai.equals("Có người"))
            holder.tvBan.setBackgroundColor(Color.GREEN);
        else
            holder.tvBan.setBackgroundColor(Color.BLUE);
        return view;
    }
}
