package com.ahmadpour.formapp.ui.formList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Forms;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by behrooz on 2/2/18.
 */

public class FormListAdapter extends RecyclerView.Adapter<FormListAdapter.ViewHolder>{

    private ArrayList<Forms> lstForms = new ArrayList<>();

    public FormListAdapter(ArrayList<Forms> lstForms) {
        this.lstForms = lstForms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_form_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forms form = lstForms.get(position);
        holder.lblTitle.setText(form.getId() + " - " + form.getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lbl_form_list_title)
        public TextView lblTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
