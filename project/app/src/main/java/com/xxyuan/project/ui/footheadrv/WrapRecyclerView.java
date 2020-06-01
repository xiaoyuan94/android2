package com.xxyuan.project.ui.footheadrv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViewInfos = new ArrayList<View>();
    private ArrayList<View> mFooterViewInfos = new ArrayList<View>();
    private Adapter mAdapter;

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void addHeaderView(View v) {
        mHeaderViewInfos.add(v);
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }

    public void addFooterView(View v) {
        mFooterViewInfos.add(v);
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0) {
            mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }
}
