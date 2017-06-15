package com.change.hippo.util.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/17
 * Time: 下午11:07
 */
public class ByteBufferTests {

    public static void main(String[] args) throws Exception {
//        testReadChannelToBuffer();
//        testByteBuffer2();
//        testWriteChannel();
//        testChannelSelector();
//        testWriteDataToChannel();
    }



    private static void testWriteDataToChannel() throws IOException {
        RandomAccessFile raFile = new RandomAccessFile("/Users/changealice/filechannel.log", "rw");
        FileChannel fc = raFile.getChannel();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        long size = fc.size();
        System.out.println(size);
        ByteBuffer buffer = ByteBuffer.allocate(48);
        try {
            //清空
            buffer.clear();

            //放入buffer
            buffer.put(newData.getBytes());
            //切换成读模式
            buffer.flip();


            while (buffer.hasRemaining()) {
                long position = fc.position();
                fc.position(position + size);

                fc.write(buffer);
                System.out.println(position);
            }

        } finally {
            fc.force(false);
            fc.close();
        }
    }


    private static void testWriteChannel() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/changealice/gc.log", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer charBuffer = ByteBuffer.allocate(10);
        int count = 10;
        while (count-- > 0) {
            charBuffer.put((byte) count);
        }

        charBuffer.compact();
        channel.write(charBuffer);
        charBuffer.clear();
        channel.close();
        randomAccessFile.close();
    }

    private static void testByteBuffer2() {
        IntBuffer buffer = IntBuffer.allocate(1024);
        int c = 1024;

        //write
        while (c-- > 0) {
            buffer.put(c);
        }
        //switch read mode
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

        buffer.clear();

    }

    private static void testReadChannelToBuffer() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/changealice/gc.log", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = channel.read(buf);
        while (bytesRead > -1) {
            System.out.println("read:" + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = channel.read(buf);
        }
        randomAccessFile.close();
    }
}
