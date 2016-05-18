package com.change.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: change.long
 * Date: 16/5/18
 * Time: 下午2:14
 * thymeleaf 模板引擎
 */
@Controller
public class TemplateEngineController {


    @RequestMapping("index")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("host", "www.change.com");
        return "index";
    }

}
