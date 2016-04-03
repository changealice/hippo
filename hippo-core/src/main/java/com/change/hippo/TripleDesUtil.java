package com.change.hippo;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * User: change.long
 * Date: 16/4/3
 * Time: 下午11:16
 * 3DES 加密解密
 */
public class TripleDesUtil {

    public final static String ALGORITHM = "DESede";

    /**
     * 初始化 3DEs KEY
     * @return
     * @throws Exception
     */
    public static byte[] initKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(56 * 3);//112 168
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }


    /**
     * 3Des 加密
     * @param key 3Des 加密的时候的key
     * @param data 加密的byte数组
     * @return 加密后的数据
     * @throws Exception 错误
     */
    public static byte[] encrypt(byte[] key,byte[] data) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);//DESedeECB/PKCS5Padding
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    /**
     * 3Des 解密
     * @param key 解密的key
     * @param data 解密的字符串
     * @return 解密的字节数组
     * @throws Exception
     */
    public static byte[] decrypt(byte[] key, byte[] data) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);//DESede/ECB/PKCS5Padding
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

}
