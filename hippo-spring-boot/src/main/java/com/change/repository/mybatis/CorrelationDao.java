package com.change.repository.mybatis;

import com.change.domain.Correlation;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * change.long@99bill.com
 *
 * 16/9/5 上午11:31
 */
@Component
public class CorrelationDao {

    @Autowired
    private SqlSession sqlSession;

    public Correlation findByMemberCode(long memberCode) {
        return this.sqlSession.selectOne("findByMemberCode", memberCode);
    }
}
