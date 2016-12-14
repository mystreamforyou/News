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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/21
 **/


public class NewsAdapter extends RealmRecyclerViewAdapter<News, NewsAdapter.NewsViewHolder> {

    public static final int TYPE_IMG_SINGLE = 1;
    public static final int TYPE_IMG_MULTI = 3;

    private Context context;
    private LayoutInflater inflater;

    public NewsAdapter(OrderedRealmCollection<News> data, Context context) {
        super(context, data, true);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        News news = getData().get(position);
        if (TextUtils.isEmpty(news.getThumbnailPicS02()) || TextUtils.isEmpty(news.getThumbnailPicS03()) || news.getThumbnailPicS02().equals(news.getThumbnailPicS03())) {
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
        final News news = getData().get(position);
        holder.title.setText(news.getTitle());
        holder.author.setText(news.getAuthorName());
        holder.date.setText(news.getDate());
        Glide.with(context).load(news.getThumbnailPicS()).into(holder.image1);
        if (TYPE_IMG_MULTI == getItemViewType(position)) {
            Glide.with(context).load(news.getThumbnailPicS02()).into(holder.image2);
            Glide.with(context).load(news.getThumbnailPicS03()).into(holder.image3);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("title", news.getTitle());
                intent.putExtra("url", news.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getData() == null ? null : getData().size();
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
