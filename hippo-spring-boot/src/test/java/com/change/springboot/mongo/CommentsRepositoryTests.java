package com.change.springboot.mongo;

import com.change.SampleSimpleApplication;
import com.change.domain.Comments;
import com.change.domain.Location;
import com.change.repository.CommentsRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * User: change.long
 * Date: 16/5/19
 * Time: 下午5:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleSimpleApplication.class)
public class CommentsRepositoryTests {


    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsRepositoryTests.class);
    @Autowired
    private CommentsRepository commentsRepository;

    @Before
    public void setUp() throws Exception {
        commentsRepository.deleteAll();
    }

    @Test
    public void testComments() throws Exception {

        Location location1 = new Location("上海", 2008);
        Location location2 = new Location("合肥", 2010);
        Location location3 = new Location("广州", 2001);
        Location location4 = new Location("马鞍山", 2012);

        Collection<Location> locations = new LinkedHashSet<Location>();
        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);
        //创建三个评论
        List<Comments> commentsList = new ArrayList<Comments>(3);
        Comments comments1 = new Comments(1L, "1", "太好了", 123L);
        comments1.setLocations(locations);
        commentsList.add(comments1);
        Comments comments2 = new Comments(2L, "2", "太棒了", 123L);
        commentsList.add(comments2);
        comments2.setLocations(locations);
        Comments comments3 = new Comments(3L, "3", "棒棒哒", 123L);
        commentsList.add(comments3);
        comments3.setLocations(locations);
        commentsRepository.save(commentsList);

        commentsList = commentsRepository.findAll();
        LOGGER.info("commentsList={}", commentsList);
        Assert.assertEquals(3, commentsList.size());

        //删除一个，验证总数
        Comments c1 = commentsRepository.findOne(1l);
        commentsRepository.delete(c1);
        Assert.assertEquals(2, commentsRepository.findAll().size());


        //删除一个，验证总数
        Comments c2 = commentsRepository.findOne(2l);
        commentsRepository.delete(c2);
        Assert.assertEquals(1, commentsRepository.findAll().size());
    }

    @Test
    public void testBatchWithStopWatch() {
        for (int index = 0; index < 1000; index++) {
            List<Comments> commentsList = new ArrayList<Comments>();
            StopWatch stopWatch = new StopWatch("testBatchWithStopWatch" + index);
            for (long i = 10; i < 110; i++) {
                commentsList.add(new Comments(i, "1", "太好了" + i, 123L));
            }
            stopWatch.start();
            commentsRepository.save(commentsList);
            stopWatch.stop();
            System.out.println(stopWatch.getId() + ":" + stopWatch.getTotalTimeSeconds());
        }
    }
}
