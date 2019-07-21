//package com.change.hippo.sbdemo.domain;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    User findByUsername(String username);
//
//    @Query("from User u where u.username=:username")
//    User findUser(@Param("username") String username);
//}
