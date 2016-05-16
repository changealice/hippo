package com.change.web;

import com.change.domain.User;
import com.change.repository.UserRepository;
import com.change.service.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/users/findAll")
    public ResponseEntity<List<User>> findAll() {
        List<User> userList = userRepository.findAll(new Sort(Sort.Direction.DESC,"id"));
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @RequestMapping("/users/add")
    public ResponseEntity<User> add(@RequestBody User user) {
        User successUser = userRepository.save(user);
        return new ResponseEntity<User>(successUser, HttpStatus.OK);
    }

    @RequestMapping("/users/del/{id}")
    public ResponseEntity<Boolean> add(@PathVariable("id") Long id) {
        userRepository.delete(id);
        return new ResponseEntity<Boolean>(HttpStatus.OK);
    }

    @RequestMapping("/reload")
    public ResponseEntity<Boolean> reload() {
        helloWorldService.testSpringDevToolReloadClasses();
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
}
