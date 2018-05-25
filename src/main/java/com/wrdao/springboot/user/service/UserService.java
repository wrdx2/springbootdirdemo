package com.wrdao.springboot.user.service;

import com.wrdao.springboot.user.dao.UserRepository;
import com.wrdao.springboot.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByName(String name) {
        User user = null;
        try {
            user = userRepository.findByUserName(name);
        } catch (Exception e) {
            System.out.println("查询出错!!");
        }
        return user;
    }
}