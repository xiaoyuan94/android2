package com.xxyuan.project.ui.footheadrv;


import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter {
    private RecyclerView.Adapter mAdapter;
    ArrayList<View> mHeaderViewInfos;
    ArrayList<View> mFooterViewInfos;

    public HeaderViewRecyclerAdapter(ArrayList<View> headerViewInfos,
                                     ArrayList<View> footerViewInfos, RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        if (headerViewInfos == null) {
            mHeaderViewInfos = new ArrayList<View>();
        } else {
            mHeaderViewInfos = headerViewInfos;
        }
        if (footerViewInfos == null) {
            mFooterViewInfos = new ArrayList<View>();
        } else {
            mFooterViewInfos = footerViewInfos;
        }
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        } else {
            return getFootersCount() + getHeadersCount();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int numHeaders = getHeadersCount();
        //head
        if (position < numHeaders) {
            return;
        }
        //body
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition);
                return;
            }
        }
        //footer
    }

    @Override
    public int getItemViewType(int position) {
        //判断当前条目是什么类型
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return RecyclerView.INVALID_TYPE;
        }
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        return RecyclerView.INVALID_TYPE - 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //header
        if (viewType == RecyclerView.INVALID_TYPE) {
            return new HeaderViewHolder(mHeaderViewInfos.get(0));
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {//footer
            return new HeaderViewHolder(mFooterViewInfos.get(0));
        }
        //Footer
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    public int getHeadersCount() {
        return mHeaderViewInfos.size();
    }

    public int getFootersCount() {
        return mFooterViewInfos.size();
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }
}
