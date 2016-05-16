package com.change.springboot;

import com.change.ExitException;
import com.change.service.HelloWorldService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

/**
 * User: change.long
 * Date: 16/5/14
 * Time: 上午12:15
 */
public class StartupCommandLineRunner implements CommandLineRunner {

    protected final Logger logger = LoggerFactory.getLogger(StartupCommandLineRunner.class);

    @Autowired
    private HelloWorldService helloWorldService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(helloWorldService.getHelloMessage());
        logger.info("args :{}", Arrays.toString(args));
        if (args.length > 0 && args[0].equals("exitcode")) {
            throw new ExitException();
        }
    }
}
