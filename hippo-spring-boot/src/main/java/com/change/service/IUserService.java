package com.change.service;

import com.change.domain.User;

import java.util.List;

/**
 * User: change.long
 * Date: 16/5/13
 * Time: 下午3:53
 */

public interface IUserService {

    List<User> findByUserName(final String userName);

    List<User> findByUserName2(final String userName);

    User save(User user);

    void delete(Long id);

    User findOne(User user);

}
