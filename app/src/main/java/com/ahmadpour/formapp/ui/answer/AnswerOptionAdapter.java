package com.ahmadpour.formapp.ui.answer;

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

public class AnswerOptionAdapter extends RecyclerView.Adapter<AnswerOptionAdapter.ViewHolder> {

    private long optionId = 0l;
    private ArrayList<Options> lstOptions = new ArrayList<>();

    public AnswerOptionAdapter(long optionId, ArrayList<Options> lstOptions) {
        this.optionId = optionId;
        this.lstOptions = lstOptions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_option,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Options option = lstOptions.get(position);
        if(option.getId() == optionId) {
            holder.chkOption.setChecked(true);
        }else {
            holder.chkOption.setChecked(false);
        }
        holder.lblOption.setText(option.getOption());
    }

    @Override
    public int getItemCount() {
        return lstOptions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chk_row_option)
        public CheckBox chkOption;
        @BindView(R.id.lbl_row_option)
        public TextView lblOption;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
