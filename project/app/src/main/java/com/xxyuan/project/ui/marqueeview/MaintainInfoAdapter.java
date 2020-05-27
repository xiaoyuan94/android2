package com.xxyuan.project.ui.marqueeview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xxyuan.project.R;

import java.util.List;

public class MaintainInfoAdapter extends RecyclerView.Adapter<MaintainInfoAdapter.MyViewHolder> {

    List<String> list;
    public MaintainInfoAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auto_rv, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position % 4));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_maintain);
        }

    }
}
