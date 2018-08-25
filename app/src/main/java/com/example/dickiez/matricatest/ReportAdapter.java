package com.example.dickiez.matricatest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportHolder> {
    List<String> listNama;
    Context context;

    public ReportAdapter(List<String> listNama, Context context) {
        this.listNama = listNama;
        this.context = context;
    }

    @NonNull
    @Override
    public ReportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_report, parent, false);
        return new ReportHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportHolder holder, final int position) {
        holder.tvNama.setText(listNama.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailPegawaiActivity.class);
                i.putExtra("nama", listNama.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNama.size();
    }

    public class ReportHolder extends RecyclerView.ViewHolder{
        TextView tvNama;
        public ReportHolder(View itemView) {
            super(itemView);
            tvNama = (TextView)itemView.findViewById(R.id.tv_nama_report);
        }
    }
}
