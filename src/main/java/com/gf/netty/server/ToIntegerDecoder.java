package com.gf.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author gufei.
 * @date 2018/12/27
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
            throws Exception {
        if (in.readableBytes() >= 4) {  //2
            out.add(in.readInt());  //3
        }
    }

}
