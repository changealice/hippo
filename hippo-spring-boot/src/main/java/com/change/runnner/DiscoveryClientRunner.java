package com.change.runnner;

import com.netflix.discovery.EurekaClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/4
 * Time: 上午1:57
 */
public class DiscoveryClientRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryClientRunner.class);
    @Autowired
    private EurekaClient discoveryClient;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("discoveryClient={}", discoveryClient);
    }
}
