package com.change.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * User: change.long
 * Date: 16/5/23
 * Time: 下午5:22
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HashMap<String, Object> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        HashMap<String, Object> r = new HashMap<String, Object>();
        r.put("message", e.getMessage());
        r.put("url", req.getRequestURL().toString());
        r.put("code", 500);
        return r;
    }
}
