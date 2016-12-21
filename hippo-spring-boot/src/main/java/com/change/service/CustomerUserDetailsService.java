//package com.change.service;
//
//import com.change.domain.SysUser;
//import com.change.repository.SysUserRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
///**
// * User: change.long@99bill.com
// * Date: 2016/12/16
// * Time: 下午6:11
// */
//public class CustomerUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private SysUserRepository sysUserRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser sysUser = sysUserRepository.findByUsername(username);
//        if (null == sysUser) {
//            throw new UsernameNotFoundException("用户不存在");
//        }
//        return sysUser;
//    }
//}
