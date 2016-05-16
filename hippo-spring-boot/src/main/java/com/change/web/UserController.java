package com.change.web;

import com.change.domain.User;
import com.change.repository.UserRepository;
import com.change.service.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * User: change.long
 * Date: 16/5/13
 * Time: 下午4:01
 */
@RestController
public class UserController {
    @Value("${spring.config.name}")
    private String name;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping("/")
    String index() {
        return "Greetings from Spring Boot!";
    }

    /**
     * 查询用户列表
     * @return users
     */
    @RequestMapping(value = "/users", method = GET)
    public ResponseEntity<List<User>> findAll() {
        List<User> userList = userRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    /**
     * 创建一个用户
     * @param user 用户json信息
     * @return user信息
     */
    @RequestMapping(value = "/users", method = POST)
    public ResponseEntity<User> add(@ModelAttribute User user) {
        User successUser = userRepository.save(user);
        return new ResponseEntity<User>(successUser, HttpStatus.OK);
    }

    /**
     * 获取一个用户
     * @param id 用户id
     * @return 用户
     */
    @RequestMapping(value = "/users/{id}", method = GET)
    public ResponseEntity<User> findOne(@PathVariable("id") Long id) {
        User successUser = userRepository.findOne(id);
        return new ResponseEntity<User>(successUser, HttpStatus.OK);
    }


    /**
     * 更新一个用户
     * @param id 用户id
     * @return 用户
     */
    @RequestMapping(value = "/users/{id}", method = PUT)
    public ResponseEntity<User> merge(@PathVariable("id") Long id, @ModelAttribute User user) {
        user.setId(id);
        User successUser = userRepository.save(user);
        return new ResponseEntity<User>(successUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = DELETE)
    public ResponseEntity<Boolean> add(@PathVariable("id") Long id) {
        userRepository.delete(id);
        return new ResponseEntity<Boolean>(HttpStatus.OK);
    }

    @RequestMapping("/reload")
    public ResponseEntity<Boolean> reload() {
        helloWorldService.testSpringDevToolReloadClasses();
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
