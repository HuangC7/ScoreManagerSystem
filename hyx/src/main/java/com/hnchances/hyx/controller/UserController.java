package com.hnchances.hyx.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.hnchances.hyx.commom.Error;
import com.hnchances.hyx.commom.Result;
import com.hnchances.hyx.entity.User;
import com.hnchances.hyx.security.util.SecurityUtil;
import com.hnchances.hyx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * (User)表控制层
 *
 */
@RestController
@Api(tags = "用户")
@RequestMapping("/api/user")
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @ApiOperation(value = "更新用户信息")
    @PostMapping("/updateById")
    @RequiresPermissions({"roles:user:update"})
    public Result update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    @ApiOperation(value = "删除用户信息")
    @PostMapping("/removeByIds")
    @RequiresRoles({"1"})  //0-学生，1-老师，2- 领导或管理（可看所有班级）
    @RequiresPermissions({"roles:user:del"})
    public Result removeById(@RequestBody User user) {
        //软删除
        int delete = userService.deleteUserById(user);
        return delete > 0 ? Result.success() : Result.error(Error.ERROR_1);
    }

    @ApiOperation(value = "查询用户信息")
    @GetMapping("/selectUserInfo")
    @RequiresPermissions({"roles:user:userinfo"})
    public Result select() {
        User user = SecurityUtil.getCurrentUser();
        return Result.success(user);
    }

    @ApiOperation(value = "注销登录，前提是在登录状态")
    @PostMapping("/loginOut")
    public Result loginOut() {
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return Result.error(Error.ERROR_1);
        }
        SecurityUtils.getSubject().logout();
        return Result.success("注销成功");
    }

}

