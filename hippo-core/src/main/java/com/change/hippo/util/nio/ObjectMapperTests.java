package com.change.hippo.util.nio;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class ObjectMapperTests {

    ObjectMapper objectMapper;

    public static void main(String[] args) throws IOException {

        ObjectMapperTests tests = new ObjectMapperTests();
        long start = System.currentTimeMillis();
        tests.readValue();

        long end = System.currentTimeMillis();


        System.out.println(end - start);
        start = System.currentTimeMillis();
        tests.readValue();
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void readValue() throws IOException {
        getObjectMapper().readValue("{\n" +
                "    \"a\":\"true\"\n" +
                "}", Map.class);
    }

    private ObjectMapper getObjectMapper() {
        if (this.objectMapper == null) {
            this.objectMapper = new ObjectMapper();
        }
        return this.objectMapper;
    }
}
