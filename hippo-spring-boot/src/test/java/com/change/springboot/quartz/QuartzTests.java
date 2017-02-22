package com.change.springboot.quartz;

import com.change.HippoSpringBootServerApplication;
import com.change.quartz.HelloJob;
import com.change.quartz.QuartzUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: change.long
 * Date: 16/5/20
 * Time: 下午2:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HippoSpringBootServerApplication.class)
public class QuartzTests {

    private static final Logger logger = LoggerFactory.getLogger(QuartzUtils.class);
    @Test
    public void testQuartz() throws Exception {
        HelloJob helloJob = new HelloJob();
        Map<String, String> jobParams = new HashMap<String, String>();
        String jobId = HelloJob.class.getName();
        jobParams.put("id", jobId);
        jobParams.put("name", "change");
        //第一次执行
        QuartzUtils.createJob(helloJob, jobParams, new Date());
        Thread.sleep(1000L);
        //第二次执行
        QuartzUtils.createJob(helloJob, jobParams, new Date());
        Thread.sleep(1000L);

    }

    @Test
    public void testQuartz1() throws Exception {
        HelloJob helloJob = new HelloJob();
        Map<String, String> jobParams = new HashMap<String, String>();
        String jobId = HelloJob.class.getName();
        jobParams.put("id", jobId);
        jobParams.put("name", "change");
        //第一次执行,一分钟执行一次
        QuartzUtils.createJob(helloJob, jobParams, "0 */1 * * * ?");
        logger.info("第一次执行,一分钟执行一次");
        Thread.sleep(130 * 1000l);
        //第二次执行，等待上面执行二次，修改为二分钟执行一次
        logger.info("第二次执行，等待上面执行二次，修改为二分钟执行一次");
        QuartzUtils.createJob(helloJob, jobParams, "0 */2 * * * ?");
        Thread.sleep(250 * 1000l);

        //修改定时任务时间
        QuartzUtils.updateJobTime(jobId, "0 */1 * * * ?");
        logger.info("第三次执行，修改定时任务时间，修改为一分钟执行一次");
        Thread.sleep(130 * 1000l);


    }

    @Test
    public void testCreatorAndDel() throws Exception {
        //测试删除
        HelloJob helloJob = new HelloJob();
        Map<String, String> jobParams = new HashMap<String, String>();
        String jobId = HelloJob.class.getName();
        jobParams.put("id", jobId);
        jobParams.put("name", "change");
        QuartzUtils.createJob(helloJob, jobParams, new Date());
        QuartzUtils.removeJob(jobId);

    }
}
