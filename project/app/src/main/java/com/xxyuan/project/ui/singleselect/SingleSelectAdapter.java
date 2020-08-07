package com.xxyuan.project.ui.singleselect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xxyuan.project.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleSelectAdapter extends RecyclerView.Adapter<SingleSelectAdapter.ViewHolder> {

    private Context context;
    private List<SingleSelectVo> list;
    private Map<String, Boolean> map = new HashMap<>();


    @NonNull
    @Override
    public SingleSelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_select, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleSelectAdapter.ViewHolder holder, int position) {
        SingleSelectVo singleSelectVo = list.get(position);
        holder.tv_select.setText(singleSelectVo.getName());
        if (singleSelectVo.isSelect()) {
            holder.tv_select.setTextColor(context.getResources().getColor(R.color.btn9));
        } else {
            holder.tv_select.setTextColor(context.getResources().getColor(R.color.contents_text));
        }
        holder.tv_select.setSelected(singleSelectVo.isSelect());

        holder.tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (singleSelectVo.isSelect()) {
                    singleSelectVo.setSelect(false);
                } else {
                    singleSelectVo.setSelect(true);
                }
                map.put(singleSelectVo.getName(),singleSelectVo.isSelect());
                notifyMap(singleSelectVo.getName());
                notifyDataSetChangedList();
            }
        });
    }

    private void notifyDataSetChangedList() {
        for (SingleSelectVo vo :list){
            Boolean aBoolean = map.get(vo.getName());
            vo.setSelect(aBoolean);
        }
        notifyDataSetChanged();
    }

    private void notifyMap(String name) {
        for(Map.Entry<String, Boolean> entry : map.entrySet()){
            String mapKey = entry.getKey();
            if (!name.equals(mapKey)){
                entry.setValue(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void notifyDataSetChanged(List<SingleSelectVo> list) {
        this.list = list;
        addMap();
        notifyDataSetChanged();
    }

    private void addMap() {
        for (SingleSelectVo vo :list){
            map.put(vo.getName(),vo.isSelect());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_select;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_select = itemView.findViewById(R.id.tv_select);
        }
    }
}
