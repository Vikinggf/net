package com.gf.netty.server.longconn;

import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;
import lombok.Data;

/**
 * @author gufei.
 * @date 2018/12/28
 */
@Data
public class LiveChannelCache {
    private Channel channel;
    private ScheduledFuture scheduledFuture;

    public LiveChannelCache(Channel channel, ScheduledFuture scheduledFuture) {
        this.channel = channel;
        this.scheduledFuture = scheduledFuture;
    }
}
