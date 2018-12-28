package com.gf.netty.server.longconn;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.StringUtil;

/**
 * @author gufei.
 * @date 2018/12/28
 */
public class LiveEncoder extends MessageToByteEncoder<LiveMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LiveMessage liveMessage, ByteBuf byteBuf) throws Exception {
        byteBuf.writeByte(liveMessage.getType());
        byteBuf.writeInt(liveMessage.getLength());
        if (!StringUtil.isNullOrEmpty(liveMessage.getContent())) {
            byteBuf.writeBytes(liveMessage.getContent().getBytes());
        }
    }
}
