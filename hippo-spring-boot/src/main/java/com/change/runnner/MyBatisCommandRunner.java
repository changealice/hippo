package com.change.runnner;

import com.change.domain.Correlation;
import com.change.repository.mybatis.CorrelationDao;
import com.change.repository.mybatis.CorrelationMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * change.long@99bill.com
 *
 * 16/9/5 上午11:40
 */
public class MyBatisCommandRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisCommandRunner.class);


    @Autowired
    private CorrelationMapper correlationMapper;

    @Autowired
    private CorrelationDao correlationDao;

    @Override
    public void run(String... args) throws Exception {

        LOGGER.info("start MyBatisCommandRunner ....");


        Correlation correlation = correlationMapper.findByMemberCode(10012864231L);
        LOGGER.info("correlationMapper correlation={}", correlation);
        correlation = correlationDao.findByMemberCode(10012864231L);
        LOGGER.info("correlationDao correlation={}", correlation);
    }
}
