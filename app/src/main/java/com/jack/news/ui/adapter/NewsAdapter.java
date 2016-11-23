package com.jack.news.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jack.news.R;
import com.jack.news.model.news.News;
import com.jack.news.ui.activity.WebViewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/21
 **/


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public static final int TYPE_IMG_SINGLE = 1;
    public static final int TYPE_IMG_MULTI = 3;

    private List<News> newses;
    private Context context;
    private LayoutInflater inflater;

    public NewsAdapter(List<News> data, Context context) {
        newses = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        News news = newses.get(position);
        if (TextUtils.isEmpty(news.thumbnailPicS02) || TextUtils.isEmpty(news.thumbnailPicS03) || news.thumbnailPicS02.equals(news.thumbnailPicS03)) {
            return TYPE_IMG_SINGLE;
        }
        return TYPE_IMG_MULTI;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_IMG_SINGLE:
                viewHolder = new NewsViewHolder(inflater.inflate(R.layout.item_news_single, null));
                break;
            case TYPE_IMG_MULTI:
                viewHolder = new NewsViewHolder(inflater.inflate(R.layout.item_news_multi, null));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final News news = newses.get(position);
        holder.title.setText(news.title);
        holder.author.setText(news.authorName);
        holder.date.setText(news.date);
        Glide.with(context).load(news.thumbnailPicS).into(holder.image1);
        if (TYPE_IMG_MULTI == getItemViewType(position)) {
            Glide.with(context).load(news.thumbnailPicS02).into(holder.image2);
            Glide.with(context).load(news.thumbnailPicS03).into(holder.image3);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("title", news.title);
                intent.putExtra("url", news.url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newses == null ? null : newses.size();
    }


    protected class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.image1)
        ImageView image1;
        ImageView image2;
        ImageView image3;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            image2 = (ImageView) itemView.findViewById(R.id.image2);
            image3 = (ImageView) itemView.findViewById(R.id.image3);
        }
    }

}