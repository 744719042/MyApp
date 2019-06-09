package com.example.comment.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.comment.R;
import com.example.comment.model.Comment;
import com.example.imagefetcher.ImageFetcher;
import com.example.provider.ImageFetcherProvider;

import java.util.List;

public class CommentAdapter extends BaseRecyclerAdapter<Comment> {

    public CommentAdapter(List<Comment> data, Context context) {
        super(data, context, R.layout.commnet_item);
    }

    @Override
    protected void bindView(BaseRecyclerViewHolder holder, Comment comment, int position) {
        holder.setText(R.id.shop_name, comment.getName());
        holder.setText(R.id.comment_time, comment.getCommentTime());
        holder.setText(R.id.comment_text, comment.getComment());
        ImageView imageView = holder.getView(R.id.image);
        ImageFetcher imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
        imageFetcher.load(comment.getUrl()).placeHolder(R.drawable.comment_place_holder).into(imageView);
    }
}