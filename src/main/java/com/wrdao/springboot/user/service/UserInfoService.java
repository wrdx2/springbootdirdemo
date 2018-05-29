package com.wrdao.springboot.user.service;

import com.wrdao.springboot.user.dao.UserInfoDao;
import com.wrdao.springboot.user.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}