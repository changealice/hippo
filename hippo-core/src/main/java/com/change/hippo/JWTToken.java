package com.change.hippo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

public class JWTToken {

    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6gLnme6nKlp6ClfHIvoqM3RZu\n" +
            "jWLv7sU8qjIbBFoNFL3AA+NpG52zYuFMGxLFjXF74o8+03bouzvSa4bELcIvPnxP\n" +
            "Qot4WhJTmG+xw8r0wM4H4ci4mux5kQHZl1xPbzPAscp7/VvwHY31sKPpuJBkk9No\n" +
            "NDZaGYePmyiDbVbL2wIDAQAB";
    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALqAueZ7qcqWnoKV\n" +
            "8ci+iozdFm6NYu/uxTyqMhsEWg0UvcAD42kbnbNi4UwbEsWNcXvijz7Tdui7O9Jr\n" +
            "hsQtwi8+fE9Ci3haElOYb7HDyvTAzgfhyLia7HmRAdmXXE9vM8Cxynv9W/AdjfWw\n" +
            "o+m4kGST02g0NloZh4+bKINtVsvbAgMBAAECgYB/vCaW21/BJ0PNQJFm4knWjJ4O\n" +
            "fbujhS+FizPiGSrsBqanWoJovMdX04NnMqu6rlNpfBSL/R1V7NbeGVMmDgkOUxNn\n" +
            "c25M/vZie4m9cov1E3SoMkmdS/KxN3PbayfHM+2XlMqPuDq9fMUSx6GvlvHidaf7\n" +
            "5FMylWZ9C2J9BlfZ4QJBAOFZmfbc/+Ojx6o+CmdsQQvPsnB1p1TnkyTVi6zmgXN5\n" +
            "5GuDQPBdaKCLzVn4RFle1gjoy8PvikzuJCPa0faKHOkCQQDT3oQd360wQAWeBs3+\n" +
            "XAjz0q6xy8xPrj+TiAgohF5waWl20wFRK1kCR+Oe6AO5aXbdcPM4Az6rLtspMXiE\n" +
            "ZxgjAkEAmtxFVwVGgDwz4QUjhzE4fhJsMKZZqJo/Xye1BcueGCoi09NuFABM/08z\n" +
            "fmmDJttqQNMh/HMn2ngos88TTNZayQJALbV09Wj7WUyGqbg4GudKOyg57jvUoWAr\n" +
            "cS3I7MzZ/xCO40bUouliM57E2dSecRLyxQWIQ45Xp+DdAX8dzBrd1wJAd7MydhJ6\n" +
            "4T24RxGDLM5sG8+ofhsmCgUYiJFj8e9BgkHu2BQ4pqjS0/RJx/Kehb+Qd9oz+vXJ\n" +
            "fAsAH7KcQycQCw==";

    public static String getToken(String uid, int exp) throws Exception {
        final Date date = new Date(System.currentTimeMillis() + 1000 * exp);

        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(PRIVATE_KEY);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return Jwts.builder().setSubject(uid)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.RS512, privateKey)
                .compact();
    }

    public static JWTResult checkToken(String token) {
        try {

            // 生成签名公钥
            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(PUBLIC_KEY);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            final Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token)
                    .getBody();
            final String sub = claims.get("sub", String.class);
            return new JWTResult(true, sub, "合法请求", "200");
        } catch (ExpiredJwtException e) {
            return new JWTResult(false, null, "token已经过期", "201");
        } catch (Exception e) {
            return new JWTResult(false, null, "非法请求", "500");
        }
    }


    @Data
    @AllArgsConstructor
    public static class JWTResult {
        private boolean success;
        private String data;
        private String message;
        private String code;
    }
}
