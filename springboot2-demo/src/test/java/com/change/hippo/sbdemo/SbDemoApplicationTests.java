package com.change.hippo.sbdemo;

import com.change.hippo.sbdemo.domain.User;
import com.change.hippo.sbdemo.properties.MyProperties1;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SbDemoApplicationTests {


    private static final Logger log = LoggerFactory.getLogger(SbDemoApplicationTests.class);

    @LocalServerPort
    private int port;
    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Resource
    MyProperties1 myProperties1;

    @Before
    public void setUp() throws Exception {
        base = new URL("http://localhost:" + port + "/chapter1/demo1");
    }

    @Test
    public void contextLoads() {
        ResponseEntity<String> responseEntity = template.getForEntity(base.toString(), String.class);
        assertEquals(responseEntity.getBody(), "Hello battcn");
        assertEquals(myProperties1.getAge(), 22);
        assertEquals(myProperties1.getName(), "battcn");
    }


    @Test
    public void test1() throws Exception {
//        template.postForEntity("http://localhost:" + port + "/users", new User("user1", "pass1"), Integer.class);
//        log.info("[添加用户成功]\n");
//        ResponseEntity<List<User>> response2 = template.exchange("http://localhost:" + port + "/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
//        });
//        final List<User> body = response2.getBody();
//        log.info("[查询所有] - [{}]\n", body);
        Long userId = 1L;
        ResponseEntity<User> response3 = template.getForEntity("http://localhost:" + port + "/users/{id}", User.class, userId);
        log.info("[主键查询] - [{}]\n", response3.getBody());
        template.put("http://localhost:" + port + "/users/{id}", new User("user11", "pass11"), userId);
        log.info("[修改用户成功]\n");
        template.delete("http://localhost:" + port + "/users/{id}", userId);
        log.info("[删除用户成功]");
    }


    @Test
    public void testUriComponent() {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/terminals/gateway/{loid}/access/terminal");
        HashMap<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("loid", "你好");
        URI build = uriComponentsBuilder.build(uriVariables);
        System.out.println(build);
    }
}
