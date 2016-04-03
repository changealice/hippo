package com.change.hippo.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

/**
 * Hello world!
 *
 */
public class SecurityMain {
    public static void main(String[] args) throws Exception {
        final String DATA = "longfei";

        /**
         * Triple Des
         */
        byte[] tripleKey = TripleDesUtil.initKey();
        System.out.println("Triple Des Key:" + Hex.encodeHexString(tripleKey));
        byte[] tripleResult = TripleDesUtil.encrypt(tripleKey, DATA.getBytes());
        System.out.println("Triple Des 加密字符串:" + Hex.encodeHexString(tripleResult));
        byte[] triplePlainText = TripleDesUtil.decrypt(tripleKey, tripleResult);
        System.out.println("Triple Des 解密字符串:" + new String(triplePlainText));

        /**
         * Aes Des
         */
        byte[] aesKey = AESUtil.initKey();
        System.out.println("AES Des Key:" + Hex.encodeHexString(aesKey));
        byte[] aesKeyResult = AESUtil.encrypt(aesKey, DATA.getBytes());
        System.out.println("AES Des 加密字符串:" + Hex.encodeHexString(aesKeyResult));
        byte[] aesPlainText = AESUtil.decrypt(aesKey, aesKeyResult);
        System.out.println("AES Des 解密字符串:" + new String(aesPlainText));

        String base64Encode = (Base64.encodeBase64String(DATA.getBytes()));
        System.out.println("BASE64 编码：" + base64Encode);
        System.out.println("BASE64 解码：" + StringUtils.newStringUtf8(Base64.decodeBase64(base64Encode)));

    }
}
