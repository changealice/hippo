package com.change.hippo;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * User: change.long
 * Date: 16/4/3
 * Time: 下午11:49
 * Aes 工具类
 */
public class AESUtil {


    public final static String ALGORITHM = "AES";

    /**
     * 初始化AES 加密的key
     * @return AES 的key
     * @throws Exception
     */
    public static byte[] initKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128);//128,192,256
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }


    /**
     * AES 加密
     * @param key 3Des 加密的时候的key
     * @param data 加密的byte数组
     * @return 加密后的数据
     * @throws Exception 错误
     */
    public static byte[] encrypt(byte[] key, byte[] data) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);//DESedeECB/PKCS5Padding
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    /**
     * AES 解密
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
