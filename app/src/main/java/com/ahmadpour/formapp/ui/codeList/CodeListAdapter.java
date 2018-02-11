package com.ahmadpour.formapp.ui.codeList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Codes;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by behrooz on 2/11/18.
 */

public class CodeListAdapter extends RecyclerView.Adapter<CodeListAdapter.ViewHolder>{

    private ArrayList<Codes> codes = new ArrayList<>();

    public CodeListAdapter(ArrayList<Codes> codes) {
        this.codes = codes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_follow_code,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Codes code = codes.get(position);
        holder.lblForm.setText(code.getForm().getTitle());
        holder.lblDate.setText(code.getDate());
        holder.lblCode.setText(code.getCode());
    }

    @Override
    public int getItemCount() {
        return codes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lbl_row_follow_code_form)
        public TextView lblForm;
        @BindView(R.id.lbl_row_follow_code_date)
        public TextView lblDate;
        @BindView(R.id.lbl_row_follow_code)
        public TextView lblCode;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
