package com.change.hippo;

import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

@RunWith(MockitoJUnitRunner.class)
public class JWTTokenTest {


    @Test
    public void getToken() throws Exception {
        final String token = JWTToken.getToken("1123123", 5);
        System.out.println(token);
        JWTToken.JWTResult jwtResult = JWTToken.checkToken(token);
        Assert.isTrue(jwtResult.isSuccess());

        TimeUnit.SECONDS.sleep(5);
        jwtResult = JWTToken.checkToken(token);
        Assert.isTrue(!jwtResult.isSuccess());
    }
}