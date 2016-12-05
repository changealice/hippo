package com.change.web;

import com.change.domain.User;
import com.change.repository.UserRepository;
import com.change.service.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    /**
     * 查询用户列表
     *
     * @return users
     */
    @ApiOperation(value = "获取用户列表", notes = "")
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> userList = userRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    /**
     * 创建一个用户
     *
     * @param user 用户json信息
     * @return user信息
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User", paramType = "body")
    @PostMapping(value = "/users")
    public ResponseEntity<User> add(@RequestBody User user) {
        User successUser = userRepository.save(user);
        return new ResponseEntity<User>(successUser, HttpStatus.OK);
    }

    /**
     * 获取一个用户
     *
     * @param id 用户id
     * @return 用户
     */
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> findOne(@PathVariable("id") Long id) {
        User successUser = userRepository.findOne(id);
        return new ResponseEntity<User>(successUser, HttpStatus.OK);
    }


    /**
     * 更新一个用户
     *
     * @return 用户
     */
    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User", paramType = "body")
    })
    @PutMapping(value = "/users/")
    public ResponseEntity<User> merge(@RequestBody User user) {
        User successUser = userRepository.save(user);
        return new ResponseEntity<User>(successUser, HttpStatus.OK);
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "通过用户ID查询单个用户", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        userRepository.delete(id);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @ApiOperation(value = "测试spring dev tools", notes = "通过重启来加载class")
    @GetMapping(value = "/reload")
    public ResponseEntity<Boolean> reload() {
        helloWorldService.testSpringDevToolReloadClasses();
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
