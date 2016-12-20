package com.change.springboot;

import com.change.SampleSimpleApplication;
import com.change.domain.User;
import com.change.repository.UserRepository;
import com.change.service.IUserService;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import javax.annotation.Resource;

/**
 * User: change.long
 * Date: 16/5/18
 * Time: 下午3:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SampleSimpleApplication.class)
public class UserServiceTests {

    @Resource(name = "userJPASupportService")
    private IUserService userJPAService;

    @Resource(name = "userJdbcSupportService")
    private IUserService userJdbcService;

    @Resource
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByUserNameJPA() throws Exception {
        List<User> list = userJPAService.findByUserName("赵磊");
        list = userJPAService.findByUserName("赵磊");
        Assert.assertNotNull(list);
    }

    @Test
    public void findByUserNameJdbc() throws Exception {
        List<User> list = userJdbcService.findByUserName("赵磊");
        Assert.assertNotNull(list);
    }

    @Test
    public void findJPAQuery() throws Exception {
        List<User> userResult = userJPAService.findByUserName2("赵磊");
        Assert.assertNotNull(userResult);
    }

    @Test
    public void findAllEhCacheTests() throws Exception {
        userRepository.findOne(11l);
        userRepository.findOne(11l);
    }

    @Test
    public void userJPAServiceSave() throws Exception {
        User user = new User();
        user.setUserName(RandomUtils.nextLong() + "");
        user.setPassword(RandomUtils.nextLong() + "");
        user = userJPAService.save(user);

        User userCache = userJPAService.findOne(user);

        userJPAService.delete(user.getId());
    }
}
