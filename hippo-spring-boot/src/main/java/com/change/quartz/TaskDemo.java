package com.change.quartz;

import com.change.domain.User;
import com.change.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: change.long
 * Date: 16/5/13
 * Time: 下午3:45
 */
@Component
public class TaskDemo {


    private Logger logger = LoggerFactory.getLogger(TaskDemo.class);
    @Autowired
    private UserRepository userRepository;


    public TaskDemo(){
        logger.info("TaskDemo Starting!");
    }
    /**
     * 一分钟执行一次
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void doSomething() {
        logger.info("TaskDemo Running,Time:{}", System.currentTimeMillis());
        List<User> userList = userRepository.findAll();
        // something that should execute on weekdays only
        logger.info("userService findAll User size :{} ", userList.size());
    }

}
