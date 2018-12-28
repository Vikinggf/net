package com.gf.netty.server.longconn;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author gufei.
 * @date 2018/12/28
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "newObj")
public class LiveMessage {
    public static final byte TYPE_HEART = 1;
    public static final byte TYPE_MESSAGE = 2;

    private byte type;
    private int length;
    private String content;
}
