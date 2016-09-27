package com.change.repository.mybatis;

import com.change.domain.Correlation;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * change.long@99bill.com
 *l
 * 16/9/5 上午11:31
 */
@Mapper
public interface CorrelationMapper {

    Correlation findByMemberCode(@Param("memberCode") Long memberCode);
}
