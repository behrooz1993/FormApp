package com.ahmadpour.formapp.ui.answers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmadpour.formapp.R;
import com.ahmadpour.formapp.data.db.models.Answers;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by behrooz on 2/10/18.
 */

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder>{

    private ArrayList<String> answers;

    public AnswersAdapter(ArrayList<String> answers) {
        this.answers = answers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_answers_date,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.lblDate.setText(answers.get(position));
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lbl_row_answers_date)
        public TextView lblDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
