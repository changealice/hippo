package com.change.service;

import com.change.domain.User;
import com.change.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: change.long
 * Date: 16/5/18
 * Time: 下午3:07
 */
@Service("userJPASupportService")
public class UserJPASupportService implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> findByUserName2(String userName) {
        return userRepository.findUsers(userName);
    }

    @Override
    @CachePut(value = "users", key = "#user.id")
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "users")
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    @Cacheable(value = "users", key = "#user.id")
    public User findOne(User user) {
        return userRepository.findOne(user.getId());
    }
}
