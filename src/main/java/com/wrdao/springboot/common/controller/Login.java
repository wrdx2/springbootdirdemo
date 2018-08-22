package com.wrdao.springboot.common.controller;

import com.wrdao.springboot.common.qo.LoginQo;
import com.wrdao.springboot.util.ControllerResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Login {

    @RequestMapping(value = "/login")
    public String index() {
        return "user/index";
    }

    @ResponseBody
    @RequestMapping("/ajaxLogin")
    public ControllerResult doLogin(LoginQo loginQo, HttpServletRequest request) {
        /*String kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
        if (!kaptcha.equals(loginQo.getKaptcha())) {
            return ControllerResult.error("error",  "验证码错误");
        }*/
        if (!ObjectUtils.isEmpty(request.getAttribute("kaptchaError"))) {
            return ControllerResult.error("kaptchaError",  request.getAttribute("kaptchaError"));
        }
        UsernamePasswordToken token = new UsernamePasswordToken(loginQo.getUsername(),loginQo.getPassword());
        Subject subject = SecurityUtils.getSubject();
        /*try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        subject.login(token);
        return ControllerResult.success(subject.getSession());
    }
}
