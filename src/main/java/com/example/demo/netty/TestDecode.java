package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import org.junit.Assert;
import org.junit.Test;

/**
 * channelhander 单元测试
 * @Author TAO
 * @Date 2018/6/8 11:43
 */
public class TestDecode {
    @Test
    public void testDecode(){
        ByteBuf buffer = Unpooled.buffer();

        for(int i=0;i<9;i++){
            buffer.writeByte(i);
        }

        ByteBuf input = buffer.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));

        Assert.assertFalse(channel.writeInbound(input.readBytes(2)));
        Assert.assertTrue(channel.writeInbound(input.readBytes(7)));

        Assert.assertTrue(channel.finish());  //5
        ByteBuf read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buffer.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
       /* byte[] array = read.array();
        System.out.println(array);*/
        int i = read.readableBytes();
        byte[] bytes = new byte[i];
        read.getBytes(read.readerIndex(), bytes);
        for (int j = 0; j < i; j++) {
            System.out.println(bytes[j]);
        }

        Assert.assertEquals(buffer.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buffer.readSlice(3), read);
        read.release();

        Assert.assertNull(channel.readInbound());
        buffer.release();
    }

}
