package com.change.hippo.util;

import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * User: change.long
 * Date: 16/4/4
 * Time: 上午12:10
 * 消息摘要算法实现
 */
public class MessageDigestUtil {


    /**
     * md5 摘要算法
     * @param data 摘要的字符串数组字节
     * @return 128位16进制字节数组
     * @throws Exception
     */
    public static byte[] digestMd5(final byte[] data) throws Exception {
        //初始化MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //更新
        messageDigest.update(data);
        //生成摘要
        return messageDigest.digest();
    }

    public static byte[] digest(final byte[] data, final String algorithm) throws Exception {
        //初始化MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        //更新
        messageDigest.update(data);
        //生成摘要
        return messageDigest.digest();
    }

    public static byte[] digestOfFile(final String filePath, final String algorithm) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);//初始化指定的摘要类
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));//输入流
        DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, messageDigest);//构造摘要流
        byte[] buffer = new byte[1024];//buffer 数组
        int read = digestInputStream.read(buffer);//读取
        while (read != -1) {//一直读到流末尾
            read = digestInputStream.read(buffer, 0, 1024);
        }
        return digestInputStream.getMessageDigest().digest();//生成摘要返回
    }

    public static byte[] digestMd5OfFile(final String filePath) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
        byte[] buffer = new byte[1024];
        int read = digestInputStream.read(buffer);
        while (read != -1) {
            read = digestInputStream.read(buffer, 0, 1024);
        }
        return digestInputStream.getMessageDigest().digest();
    }

    /**
     * sha 摘要算法
     * @param data data 摘要字符串数组字节
     * @return 160位16进制 字节数组
     * @throws Exception
     */
    public static byte[] digestSha(final byte[] data) throws Exception {
        //初始化MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        //更新
        messageDigest.update(data);
        //生成摘要
        return messageDigest.digest();
    }

    public static byte[] digestShaOfFile(final String filePath) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
        byte[] buffer = new byte[1024];
        int read = digestInputStream.read(buffer);
        while (read != -1) {
            read = digestInputStream.read(buffer, 0, 1024);
        }
        return digestInputStream.getMessageDigest().digest();
    }

    /**
     * 初始化hmac算法key
     * @return
     * @throws Exception
     */
    public static byte[] initHmacKey(final String algorithm) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static byte[] digestHmac(final byte[] data, final byte[] key, final String algorithm) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, algorithm);
        //初始化摘要类
        Mac macDigest = Mac.getInstance(algorithm);
        macDigest.init(secretKeySpec);
        return macDigest.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        final String DATA = "jikexueyuan";
        System.out.println(Hex.encodeHexString(MessageDigestUtil.digestMd5(DATA.getBytes())));
        System.out.println(Hex.encodeHexString(MessageDigestUtil.digestSha(DATA.getBytes())));

        String filePath = "/Users/changealice/Downloads/应用日志测试类.docx";
        String hexStringOfMd5 = Hex.encodeHexString(MessageDigestUtil.digestMd5OfFile(filePath));
        System.out.println("MD5:" + hexStringOfMd5);
        String hexStringOfSha = Hex.encodeHexString(MessageDigestUtil.digestShaOfFile(filePath));
        System.out.println("SHA:" + hexStringOfSha);

        //指定摘要算法测试
        //sha-256
        System.out.println(Hex.encodeHexString(MessageDigestUtil.digest(DATA.getBytes(), "SHA-256")));
        //sha-512
        System.out.println(Hex.encodeHexString(MessageDigestUtil.digest(DATA.getBytes(), "SHA-512")));

        //hmac
        //hmac key生成：
        String hmacmd5 = "HMACMD5";
        byte[] hmacKeyBytes = MessageDigestUtil.initHmacKey(hmacmd5);
        System.out.println("HMAC HMACMD5 生成的key：" + Hex.encodeHexString(hmacKeyBytes));

        //生成hmac
        //hmacmd5
        byte[] hmacResult = MessageDigestUtil.digestHmac(DATA.getBytes(), hmacKeyBytes, hmacmd5);
        System.out.println("hmac HMACMD5 摘要结果为：" + Hex.encodeHexString(hmacResult));

        //hmac sha key生成：
        String hmacsha256 = "HMACSHA256";
        byte[] hmacSha256 = MessageDigestUtil.initHmacKey(hmacsha256);
        System.out.println("HMAC hmacsha256 生成的key：" + Hex.encodeHexString(hmacSha256));

        //生成hmac
        //hmacsha256
        byte[] hmacSha256Result = MessageDigestUtil.digestHmac(DATA.getBytes(), hmacSha256, hmacsha256);
        System.out.println("hmac hmacsha256 摘要结果为：" + Hex.encodeHexString(hmacSha256Result));

        hmacSha256Result = MessageDigestUtil.digestHmac(DATA.getBytes(), hmacSha256, "HMACSHA512");
        System.out.println("hmac HMACSHA256摘要结果为：" + Hex.encodeHexString(hmacSha256Result));
    }


}
