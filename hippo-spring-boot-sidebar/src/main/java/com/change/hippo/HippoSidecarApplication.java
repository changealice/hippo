package com.change.hippo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class HippoSidecarApplication {

    public static void main(String[] args) {
        SpringApplication.run(HippoSidecarApplication.class, args);
    }
}
