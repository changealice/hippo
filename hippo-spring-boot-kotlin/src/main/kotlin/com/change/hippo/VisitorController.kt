//package com.change.hippo
//
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.RestController
//import java.util.concurrent.atomic.AtomicLong
//
///**
// * User: change.long@99bill.com
// * Date: 2017/6/12
// * Time: 下午11:16
// */
//
//@RestController
//class VisitorController {
//
//
//    var count = AtomicLong();
//
//
//    @RequestMapping("hello")
//    fun hello(@RequestParam(value = "name", defaultValue = "change.long") name: String): String {
//        val time = count.incrementAndGet()
//        return Visitor(name, time).sayHello();
//    }
//}