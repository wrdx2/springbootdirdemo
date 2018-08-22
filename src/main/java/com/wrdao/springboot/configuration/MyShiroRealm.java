package com.wrdao.springboot.configuration;

import com.wrdao.springboot.sys.service.*;
import com.wrdao.springboot.sys.vo.SysPermissionVo;
import com.wrdao.springboot.sys.vo.SysRoleVo;
import com.wrdao.springboot.sys.vo.SysUserVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysRolePermissionService sysRolePermissionService;
    @Resource
    private SysPermissionService sysPermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUserVo sysUserVo = (SysUserVo) principals.getPrimaryPrincipal();
        List<String> roleIdList = sysUserRoleService.getRoleIdListByUserId(sysUserVo);
        for (SysRoleVo role : sysRoleService.getRoleListByRoleIdList(roleIdList)) {
            authorizationInfo.addRole(role.getRole());
            List<String> permissionIdList = sysRolePermissionService.getPermissionIdListByRoleVo(role);
            for (SysPermissionVo p : sysPermissionService.getPermissionListByPermissionIdList(permissionIdList)) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        //String password = new String((char[])token.getCredentials());

        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUserVo sysUserVo = sysUserService.findByUsername(username);
        System.out.println("----->>userInfo=" + sysUserVo);
        if (sysUserVo == null) {
            //return null;
            throw new UnknownAccountException("用户不存在");//没有找到账号异常
        }
        if (sysUserVo.getAvailable() == 0) {
            //return null;
            throw new DisabledAccountException("账号未激活");//抛出账号锁定异常
        }
        if (sysUserVo.getAvailable() == 2) {
            //return null;
            throw new LockedAccountException("账号被锁定");//抛出账号锁定异常
        }
        /*try {
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    sysUserVo, //用户名
                    sysUserVo.getPassword(), //密码
                    ByteSource.Util.bytes(sysUserVo.getCredentialsSalt()),//salt=username+salt
                    getName()  //realm name
            );
            return authenticationInfo;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new AuthenticationException(e);
        }*/
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUserVo, //用户名
                sysUserVo.getPassword(), //密码
                ByteSource.Util.bytes(sysUserVo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;

    }

}