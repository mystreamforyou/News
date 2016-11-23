package com.jack.news.model.channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/11/21
 **/


public class Channel {

    //类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)

    private static final List<Channel> mChannels = new ArrayList<>();

    static {
        mChannels.add(new Channel("top", "头条"));
        mChannels.add(new Channel("shehui", "社会"));
        mChannels.add(new Channel("guonei", "国内"));
        mChannels.add(new Channel("guoji", "国际"));
        mChannels.add(new Channel("yule", "娱乐"));
        mChannels.add(new Channel("tiyu", "体育"));
        mChannels.add(new Channel("junshi", "军事"));
        mChannels.add(new Channel("keji", "科技"));
        mChannels.add(new Channel("caijing", "财经"));
        mChannels.add(new Channel("shishang", "时尚"));
    }

    public String channelId;
    public String name;

    public Channel(String id, String name) {
        this.channelId = id;
        this.name = name;
    }

    /**
     * 根据 id 获取 Channel
     *
     * @param id
     * @return
     */
    public static Channel getChannelById(String id) {
        Channel channel = null;
        for (Channel c : mChannels) {
            if (id.equals(c.channelId)) {
                channel = c;
            }
        }
        return channel;
    }

    /**
     * 根据位置获取 Channel
     *
     * @param position
     * @return
     */
    public static Channel getChannelByPosition(int position) {
        return mChannels.get(position);
    }

    /**
     * 获取 Channel 列表
     * 获取 Channel 列表
     *
     * @return
     */
    public static List<Channel> getChannelList() {
        return mChannels;
    }

}
