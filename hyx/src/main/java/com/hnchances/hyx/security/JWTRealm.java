package com.hnchances.hyx.security;

import cn.hutool.core.util.StrUtil;
import com.hnchances.hyx.entity.User;
import com.hnchances.hyx.security.entity.JwtToken;
import com.hnchances.hyx.security.util.JwtUtil;
import com.hnchances.hyx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class JWTRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(username);

        if(user!=null){
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            Set<String> set= new HashSet<>();
            set.add(user.getIdentity().toString());
            authorizationInfo.setRoles(set);
            return authorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsernameByToken(token);
        if (StrUtil.isBlank(username)) {
            throw new AuthenticationException("token认证失败!");
        }
        User user = userService.getUserByUserName(username);
        // 判断用户
        if (user == null) {
            throw new AuthenticationException("用户不存在!");
        }
        if (user.getStatus() == 1) {
            throw new AuthenticationException("账号已被删除!");
        }
        return new SimpleAuthenticationInfo(user, token, getName());
    }
}
