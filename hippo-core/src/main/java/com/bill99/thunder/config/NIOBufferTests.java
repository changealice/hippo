package com.bill99.thunder.config;

import java.nio.IntBuffer;

/**
 * change.long@99bill.com
 * 2016/10/29
 * 上午2:32
 */
public class NIOBufferTests {


    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(10);
        intBuffer.put(101);
        System.err.println("Write mode: ");
        System.err.println("\tCapacity: " + intBuffer.capacity());
        System.err.println("\tPosition: " + intBuffer.position());
        System.err.println("\tLimit: " + intBuffer.limit());

        intBuffer.flip();
        System.err.println("Read mode: ");
        System.err.println("\tCapacity: " + intBuffer.capacity());
        System.err.println("\tPosition: " + intBuffer.position());
        System.err.println("\tLimit: " + intBuffer.limit());
    }

}
