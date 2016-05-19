package com.change.repository;

import com.change.domain.Comments;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * User: change.long
 * Date: 16/5/19
 * Time: 下午5:13
 * 评论mongodb操作类
 */
public interface CommentsRepository extends MongoRepository<Comments, Long> {

    /**
     * 按照订单号查询所有的评论内容
     * @param orderId 订单号
     * @return 列表
     */
    List<Comments> findByOrderId(Long orderId);
}
