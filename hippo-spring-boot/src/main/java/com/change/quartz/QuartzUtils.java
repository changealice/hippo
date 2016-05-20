package com.change.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.AbstractTrigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.spi.MutableTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

public class QuartzUtils {

    private static Scheduler scheduler = SpringUtil.getBean("scheduler");
    private static final Logger logger = LoggerFactory.getLogger(QuartzUtils.class);

    /**
     * 创建简单定时任务
     * @param jobBean     自定义任务类 必须继承QuartzJobBean
     * @param map           自定义任务所需要的参数 可以通过context.getJobDetail().getJobDataMap()获取
     *                                  map中必须设置一个唯一id  map.set("id","xxx")
     * @param date           定时任务执行时间
     * @throws SchedulerException
     */
    public static void createJob(QuartzJobBean jobBean, Map map, Date date) throws SchedulerException {
        String jobId = (String) map.get("id");
        TriggerKey triggerKey = triggerKey("trigger_" + jobId, Scheduler.DEFAULT_GROUP);
        Trigger trigger = newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(simpleSchedule())
                .startAt(date)
                .build();
        JobKey jobKey = JobKey.jobKey("job_" + jobId, Scheduler.DEFAULT_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            jobDetail = newJob(jobBean.getClass())
                    .withIdentity(jobKey)
                    .usingJobData(new JobDataMap(map))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(jobKey);
            scheduler.scheduleJob(jobDetail, trigger);
        }

    }

    /**
     * 创建复杂定时任务
     * @param jobBean     自定义任务类 必须继承QuartzJobBean
     * @param map           自定义任务所需要的参数 可以通过context.getJobDetail().getJobDataMap()获取
     *                                  map中必须设置一个唯一id  map.set("id","xxx")
     * @param cronExpression           cron 表达式 如：0 0 12 * * ? 每天中午12点触发
     * @throws SchedulerException
     */
    public static void createJob(QuartzJobBean jobBean, Map map, String cronExpression) throws SchedulerException, ParseException {
        String jobId = (String) map.get("id");
        TriggerKey triggerKey = triggerKey("trigger_" + jobId, Scheduler.DEFAULT_GROUP);
        JobKey jobKey = JobKey.jobKey("job_" + jobId, Scheduler.DEFAULT_GROUP);
        MutableTrigger trigger = CronScheduleBuilder.cronSchedule(cronExpression)
                                .build();
        trigger.setJobKey(jobKey);
        trigger.setKey(triggerKey);

        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            logger.info("job:id=[{}] is null creating",jobId);
            jobDetail = newJob(jobBean.getClass())
                    .withIdentity(jobKey)
                    .usingJobData(new JobDataMap(map))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            logger.info("job:id=[{}] is exists updating", jobId);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(jobKey);
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

    /**
     * 修改定时任务执行时间
     * @param jobId    任务类map里面设置的id  map.get("id");
     * @param date      定时任务执行时间
     * @throws SchedulerException
     */
    public static void updateJobTime(String jobId, Date date) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("job_" + jobId, Scheduler.DEFAULT_GROUP);
        TriggerKey triggerKey = triggerKey("trigger_" + jobId, Scheduler.DEFAULT_GROUP);
        AbstractTrigger trigger = (AbstractTrigger) scheduler.getTrigger(triggerKey);
        if (trigger != null && !trigger.getStartTime().equals(date)) {
            JobDetail jd = scheduler.getJobDetail(jobKey);
            scheduler.deleteJob(jobKey);
            trigger.setStartTime(date);
            scheduler.scheduleJob(jd, trigger);
        }
    }

    /**
     * 修改定时任务执行时间
     * @param jobId    任务类map里面设置的id  map.get("id");
     * @param cronExpression           cron 表达式 如：0 0 12 * * ? 每天中午12点触发
     * @throws SchedulerException
     */
    public static void updateJobTime(String jobId, String cronExpression) throws SchedulerException, ParseException {
        JobKey jobKey = JobKey.jobKey("job_" + jobId, Scheduler.DEFAULT_GROUP);
        TriggerKey triggerKey = triggerKey("trigger_" + jobId, Scheduler.DEFAULT_GROUP);

        CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
        if (trigger != null && !trigger.getCronExpression().equals(cronExpression)) {
            JobDetail jd = scheduler.getJobDetail(jobKey);
            scheduler.deleteJob(jobKey);
            trigger.setCronExpression(cronExpression);
            scheduler.scheduleJob(jd, trigger);
        }
    }

    /**
     * 删除定时任务
     * @param jobId  任务类map里面设置的id  map.get("id");
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void removeJob(String jobId) throws SchedulerException, ParseException {
        TriggerKey triggerKey = triggerKey("trigger_" + jobId, Scheduler.DEFAULT_GROUP);
        JobKey jobKey = JobKey.jobKey("job_" + jobId, Scheduler.DEFAULT_GROUP);

        scheduler.pauseTrigger(triggerKey);// 停止触发器
        scheduler.unscheduleJob(triggerKey);// 移除触发器
        scheduler.deleteJob(jobKey);// 删除任务
    }


}
