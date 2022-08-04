package com.hnchances.hyx.security;

import com.hnchances.hyx.entity.User;
import com.hnchances.hyx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(username);
        if(user!=null){
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            Set<String> set= new HashSet<>();
            set.add(user.getIdentity().toString());
            authorizationInfo.setRoles(set);
            //0-学生，1-老师，2- 领导或管理（可看所有班级）
            if (user.getIdentity() == 0) {
                authorizationInfo.addStringPermissions(new ArrayList<>(Arrays.asList(
                        "roles:user:select", "roles:user:update")));
                return authorizationInfo;
            } else if (user.getIdentity() == 1) {
                authorizationInfo.addStringPermissions(new ArrayList<>(Arrays.asList(
                        "roles:user:select", "roles:user:update", "roles:user:del",
                        "roles:teacher:grades:select","roles:grades:add","roles:grades:update",
                        "roles:grades:del"
                        )));
                return authorizationInfo;
            } else if (user.getIdentity() == 2) {
                authorizationInfo.addStringPermissions(new ArrayList<>(Arrays.asList(
                        "roles:user:select", "roles:user:update", "roles:user:del",
                        "roles:admin:grades:select","roles:grades:add","roles:grades:update",
                        "roles:grades:del"
                )));
                return authorizationInfo;
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.getUserByUserName(username);
        if(ObjectUtils.isEmpty(user)) {
            return null;
        }
        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),this.getName());
    }
}