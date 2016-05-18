package com.change.springboot;

import com.change.SampleSimpleApplication;
import com.change.domain.User;
import com.change.service.IUserService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import javax.annotation.Resource;

/**
 * User: change.long
 * Date: 16/5/18
 * Time: 下午3:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SampleSimpleApplication.class)
public class UserServiceTests {

    @Resource(name = "userJPASupportService")
    private IUserService userJPAService;

    @Resource(name = "userJdbcSupportService")
    private IUserService userJdbcService;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByUserNameJPA() throws Exception {
        List<User> list = userJPAService.findByUserName("赵磊");
        Assert.assertNotNull(list);
    }

    @Test
    public void findByUserNameJdbc() throws Exception {
        List<User> list = userJdbcService.findByUserName("赵磊");
        Assert.assertNotNull(list);
    }

    @Test
    public void findJPAQuery()throws Exception{
        List<User> userResult = userJPAService.findByUserName2("赵磊");
        Assert.assertNotNull(userResult);
    }
}
