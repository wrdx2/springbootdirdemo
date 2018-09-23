package com.wrdao.springboot.common.controller;

import com.wrdao.springboot.common.qo.LoginQo;
import com.wrdao.springboot.common.service.LoginService;
import com.wrdao.springboot.util.ControllerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login")
    public String index() {
        return "user/index";
    }

    @ResponseBody
    @RequestMapping("/ajaxLogin")
    public ControllerResult ajaxLogin(LoginQo loginQo, HttpServletRequest request) {

        return loginService.login(loginQo, request);
    }
}
