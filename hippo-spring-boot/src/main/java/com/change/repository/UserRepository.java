package com.change.repository;

import com.change.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.persistence.QueryHint;

/**
 * User: change.long
 * Date: 16/5/13
 * Time: 下午3:53
 */

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<User> findByUserName(final String userName);

    @Query("from User u where u.userName=:userName")
    List<User> findUsers(@Param("userName") String userName);
}
