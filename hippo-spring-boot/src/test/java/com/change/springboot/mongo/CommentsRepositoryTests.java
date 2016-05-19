package com.change.springboot.mongo;

import com.change.SampleSimpleApplication;
import com.change.domain.Comments;
import com.change.repository.CommentsRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * User: change.long
 * Date: 16/5/19
 * Time: 下午5:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleSimpleApplication.class)
public class CommentsRepositoryTests {


    @Autowired
    private CommentsRepository commentsRepository;

    @Before
    public void setUp() throws Exception {
        commentsRepository.deleteAll();
    }

    @Test
    public void testComments() throws Exception {

        //创建三个评论
        List<Comments> commentsList = new ArrayList<Comments>(3);
        commentsList.add(new Comments(1L, "1", "太好了", 123L));
        commentsList.add(new Comments(2L, "2", "太棒了", 123L));
        commentsList.add(new Comments(3L, "3", "棒棒哒", 123L));
        commentsRepository.save(commentsList);

        Assert.assertEquals(3, commentsRepository.findAll().size());

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
