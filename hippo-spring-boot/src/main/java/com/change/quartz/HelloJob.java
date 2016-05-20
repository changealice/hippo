package com.change.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * User: change.long
 * Date: 16/5/20
 * Time: 下午2:32
 */
public class HelloJob extends QuartzJobBean {


    private final Logger logger = LoggerFactory.getLogger(HelloJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String jobId = (String) context.getJobDetail().getJobDataMap().get("id");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException ignored) {
        }
        logger.info("job id:{} execute", jobId);
    }
}
