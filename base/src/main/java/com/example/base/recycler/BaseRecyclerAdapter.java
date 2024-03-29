package com.example.base.recycler;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected List<T> mData = new ArrayList<>();
    protected Context mContext;
    protected LayoutInflater mInflater;
    private @LayoutRes
    int mLayoutId;
    private MultiSupport<T> multiSupport;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public BaseRecyclerAdapter(List<T> data, Context context, int layoutId) {
        this.mData.addAll(data);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
    }

    public BaseRecyclerAdapter(List<T> data, Context context, MultiSupport<T> multiSupport) {
        this(data, context, -1);
        this.multiSupport = multiSupport;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        if (mLayoutId == -1) {
            itemView = mInflater.inflate(viewType, parent, false);
            return new BaseRecyclerViewHolder(itemView);
        } else {
            itemView = mInflater.inflate(mLayoutId, parent, false);
        }
        return new BaseRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {
        final T item = mData.get(position);
        bindView(holder, item, position);

        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(holder.itemView, position, item);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (mOnItemLongClickListener != null) {
                return mOnItemLongClickListener.onItemLongClick(holder.itemView, position, item);
            }
            return true;
        });
    }

    protected abstract void bindView(BaseRecyclerViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (multiSupport != null) {
            return multiSupport.getItemType(mData.get(position));
        }
        return super.getItemViewType(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T item);
    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(View view, int position, T item);
    }

    public interface MultiSupport<T> {
        int getItemType(T data);
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(mData);
    }

    public void resetData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void appendData(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return new ArrayList<>(mData);
    }
}
