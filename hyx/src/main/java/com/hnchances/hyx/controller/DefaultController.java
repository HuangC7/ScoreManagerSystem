package com.hnchances.hyx.controller;

import com.hnchances.hyx.commom.Error;
import com.hnchances.hyx.commom.Result;
import com.hnchances.hyx.entity.User;
import com.hnchances.hyx.service.UserService;
import com.hnchances.hyx.utils.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * author: yuexuan_huang@126.com
 * Date: 2022/8/1
 * Description:
 */

@RestController
@Api(tags = "登录和注册")
public class DefaultController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        // 校验参数
        ValidatorUtil.validateEntity(user);
        
        

        return Result.success();
    }


    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        ValidatorUtil.validateEntity(user);



        return Result.error(Error.ERROR_1);
    }


    
}
