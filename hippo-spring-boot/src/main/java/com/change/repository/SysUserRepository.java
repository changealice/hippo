package com.change.repository;

import com.change.domain.SysUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/16
 * Time: 下午6:08
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {

    SysUser findByUsername(String username);
}
