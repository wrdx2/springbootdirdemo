package com.wrdao.springboot.util;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class ExceptionalHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ControllerResult exception(Exception e) {
        return ControllerResult.error("未知错误", e);
    }

    @ExceptionHandler(value = UnknownAccountException.class)
    public ControllerResult unknownAccountException(UnknownAccountException e) {
        return ControllerResult.error("用户不存在", e.getMessage());
    }

    @ExceptionHandler(value = DisabledAccountException.class)
    public ControllerResult disabledAccountException(DisabledAccountException e) {
        return ControllerResult.error("账号未激活", e.getMessage());
    }

    @ExceptionHandler(value = LockedAccountException.class)
    public ControllerResult lockedAccountException(LockedAccountException e) {
        return ControllerResult.error("账号被锁定", e.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ControllerResult authenticationException(AuthenticationException e) {
        return ControllerResult.error("密码错误", e.getMessage());
    }

}
