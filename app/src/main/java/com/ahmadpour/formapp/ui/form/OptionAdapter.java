package com.ahmadpour.formapp.ui.form;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Options;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by behrooz on 2/2/18.
 */

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {

    private int selectedPosition = -1;
    private ArrayList<Options> lstOptions = new ArrayList<>();

    public OptionAdapter(ArrayList<Options> lstOptions) {
        this.lstOptions = lstOptions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Options option = lstOptions.get(position);
        holder.lblOption.setText(option.getOption());
        if (position == selectedPosition) {
            holder.chkOption.setChecked(true);
        } else {
            holder.chkOption.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return lstOptions.size();
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chk_row_option)
        public CheckBox chkOption;
        @BindView(R.id.lbl_row_option)
        public TextView lblOption;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
