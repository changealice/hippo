package com.change.springboot;

import com.change.SampleSimpleApplication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/**
 * User: change.long
 * Date: 16/5/18
 * Time: 下午3:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SampleSimpleApplication.class)
public class DataSourceTests {


    @Autowired
    private ApplicationContext ctx;

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDatasource;


    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(ctx);
    }

    @Test
    public void testDataSource() throws Exception {
        Assert.assertNotNull(primaryDataSource);
        Assert.assertNotNull(secondaryDatasource);
    }
}
