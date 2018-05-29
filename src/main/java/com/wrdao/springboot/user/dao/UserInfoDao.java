package com.wrdao.springboot.user.dao;

import com.wrdao.springboot.user.vo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {

    @Query("select t from UserInfo t where t.username = :username")
    UserInfo findByUsername(String username);
}
