package com.wrdao.springboot.common.filter;

import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CaptchaValidateFilter extends AccessControlFilter {
    private String captchaParam = "kaptcha"; //前台提交的验证码参数名

    private String failureKeyAttribute = "kaptchaError";  //验证失败后存储到的属性名

    public String getCaptchaCode(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        // 从session获取正确的验证码
        Session session = SecurityUtils.getSubject().getSession();
        String validateCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //页面输入的验证码
        String captchaCode = getCaptchaCode(request);

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        //判断验证码是否表单提交（允许访问）
        if (!"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            //return true;
        }

        // 若验证码为空或匹配失败则返回false
        /*if (captchaCode == null) {
            return false;
        } else if (validateCode != null) {
            captchaCode = captchaCode.toLowerCase();
            validateCode = validateCode.toLowerCase();

            if (!captchaCode.equals(validateCode)) {
                return false;
            }
        }
        return true;*/

        //只有验证码匹配成功才返回true
        if (captchaCode != null && validateCode != null) {
            captchaCode = captchaCode.toLowerCase();
            validateCode = validateCode.toLowerCase();

            return captchaCode.equals(validateCode);
        }

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //如果验证码失败了，存储失败key属性
        request.setAttribute(failureKeyAttribute, "验证码错误");
        return true;
    }

    private String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }
}
