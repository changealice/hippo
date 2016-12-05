package com.change.web;

import com.change.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.ApiOperation;

/**
 * User: change.long
 * Date: 16/5/18
 * Time: 下午2:14
 * thymeleaf 模板引擎
 */
@Controller
public class TemplateEngineController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "index", notes = "集成thymeleaf模板引擎")
    @GetMapping(value = "index")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("host", "www.change.com");
        modelMap.addAttribute("users", userRepository.findAll());
        return "index";
    }

}
