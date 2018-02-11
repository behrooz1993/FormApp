package com.ahmadpour.formapp.ui.form;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Options;

import java.util.ArrayList;

/**
 * Created by behrooz on 2/10/18.
 */

public class OptionSpinnerAdapter extends BaseAdapter{

    private ArrayList<Options> options = new ArrayList<>();

    public OptionSpinnerAdapter(ArrayList<Options> options) {
        this.options = options;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return options.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_simple_spinner,parent,false);
            holder = new ViewHolder();
            holder.lblItem = convertView.findViewById(R.id.lbl_row_simple_spinner);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lblItem.setText(options.get(position).getOption());

        return convertView;
    }

    private class ViewHolder {
        public TextView lblItem;
    }
}
