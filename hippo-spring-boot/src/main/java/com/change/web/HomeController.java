package com.change.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午7:27
 */
@RestController
public class HomeController {

    @ApiOperation(value = "首页", notes = "测试首页")
    @RequestMapping(value = "/", method = GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
