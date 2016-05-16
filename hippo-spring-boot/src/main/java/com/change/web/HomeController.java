package com.change.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午7:27
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
