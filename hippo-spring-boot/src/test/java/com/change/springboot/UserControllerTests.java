package com.change.springboot;

import com.change.web.HomeController;
import com.change.web.UserController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午2:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class UserControllerTests {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        HomeController homeController = new HomeController();
        UserController userController = new UserController();
        mvc = MockMvcBuilders.standaloneSetup(userController, homeController).build();
    }

    @Test
    public void getIndex() throws Exception {
        mvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    public void testUserController() throws Exception {
        //get查一下user列表

        RequestBuilder request = get("/users/");
        mvc.perform(request).
                andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //添加一个用户
        request = post("/users")
                .param("userName","测试大师");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("测试大师"));

        //更新一个用户
        request = put("/users/2")
                .param("userName","测试大师更新");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("测试大师更新"));

        //获取一个用户
        request = get("/users/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("测试大师更新"));

        //删除一个用户

        request = delete("/users/3");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
