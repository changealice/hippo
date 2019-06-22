package com.change.spring.cloud.util;

import feign.Feign;

public class RestApiCallUtils {

    public static <T> T getRestClient(Class<T> apiType, String url) {
        return Feign.builder().target(apiType, url);
    }
}
