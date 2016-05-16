package com.change.springboot;

import com.change.SampleSimpleApplication;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午4:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleSimpleApplication.class)
public class SpringTestSampleSimpleApplicationTests {

    @Autowired
    private ApplicationContext ctx;


    @Test
    public void testContextLoads() throws Exception {
        Assert.assertNotNull(this.ctx);
        Assert.assertTrue(this.ctx.containsBean("helloWorldService"));
        Assert.assertTrue(this.ctx.containsBean("simpleSampleApplication"));
    }
}
