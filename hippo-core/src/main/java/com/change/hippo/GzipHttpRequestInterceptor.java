package com.change.hippo;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * User: change.long
 * Date: 16/7/7
 * Time: 下午2:13
 */
public class GzipHttpRequestInterceptor implements org.springframework.http.client.ClientHttpRequestInterceptor {


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        //压缩 body
        request.getHeaders().add("Content-Encoding", "gzip");
        return execution.execute(request, body);
    }
}
