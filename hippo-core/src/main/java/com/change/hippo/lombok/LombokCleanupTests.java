package com.change.hippo.lombok;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import lombok.Cleanup;

/**
 * User: change.long
 * Date: 16/6/29
 * Time: 下午7:20
 */
public class LombokCleanupTests {


    public static void main(String[] args) throws IOException {
        @Cleanup FileInputStream inStream = new FileInputStream(new File("/Users/changealice/1.txt"));
        byte[] b = new byte[65536];
        while (true) {
            int r = inStream.read(b);
            if (r == -1) break;
        }
    }

}
