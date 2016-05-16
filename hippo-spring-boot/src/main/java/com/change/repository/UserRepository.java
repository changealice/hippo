package com.change.repository;

import com.change.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: change.long
 * Date: 16/5/13
 * Time: 下午3:53
 */

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserName(final String userName);

}
