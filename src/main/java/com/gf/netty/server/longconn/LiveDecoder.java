package com.gf.netty.server.longconn;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author gufei.
 * @date 2018/12/27
 */
public class LiveDecoder extends ReplayingDecoder<LiveDecoder.LiveState> {
    Logger logger = LoggerFactory.getLogger(LiveDecoder.class);

    public enum LiveState {
        TYPE,
        LENGTH,
        CONTENT
    }

    private LiveMessage message;

    public LiveDecoder() {
        super(LiveState.TYPE);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        LiveState state = state();
        logger.debug("state:" + state + " message:" + message);
        switch (state) {
            case TYPE:
                message = LiveMessage.newObj();
                byte type = byteBuf.readByte();
                logger.debug("type:" + type);
                message.setType(type);
                checkpoint(LiveState.LENGTH);
                break;
            case LENGTH:
                int length = byteBuf.readInt();
                message.setLength(length);
                if (length > 0) {
                    checkpoint(LiveState.CONTENT);
                } else {
                    list.add(message);
                    checkpoint(LiveState.TYPE);
                }
                break;
            case CONTENT:
                byte[] bytes = new byte[message.getLength()];
                byteBuf.readBytes(bytes);
                String content = new String(bytes);
                message.setContent(content);
                list.add(message);
                checkpoint(LiveState.TYPE);
                break;
            default:
                throw new IllegalStateException("invalid state:" + state);
        }
        logger.debug("end state:" + state + " list:" + list);
    }

}
