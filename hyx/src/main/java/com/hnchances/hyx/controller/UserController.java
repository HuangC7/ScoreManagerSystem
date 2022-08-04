package com.hnchances.hyx.controller;

import com.hnchances.hyx.Exception.BizException;
import com.hnchances.hyx.commom.Result;
import com.hnchances.hyx.entity.User;
import com.hnchances.hyx.security.util.SecurityUtil;
import com.hnchances.hyx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * (User)表控制层
 *
 */
@RestController
@Api(value = "用户", tags = "用户的相关接口")
@RequestMapping("/api/user")
public class UserController{
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @ApiOperation(value = "更新", notes = "更新用户信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "user",value = "用户",required = true)
    )
    @PostMapping("/updateById")
    @RequiresPermissions({"roles:user:update"})
    public Result update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    @ApiOperation(value = "删除",notes = "删除用户信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "user",value = "用户",required = true)
    )
    @PostMapping("/removeByIds")
    @RequiresPermissions({"roles:user:del"})
    public Result removeById(@RequestBody User user) {
        //软删除
        int delete = userService.deleteUserById(user);
        if(delete > 0){
            return Result.success();
        }else {
            throw new BizException("删除用户信息失败");
        }
    }

    @ApiOperation(value = "查询", notes = "查询用户信息")
    @GetMapping("/selectUserInfo")
    @RequiresPermissions({"roles:user:select"})
    public Result select() {
        User user = SecurityUtil.getCurrentUser();
        return Result.success(user);
    }

    @ApiOperation(value = "退出", notes = "注销登录，前提是在登录状态")
    @PostMapping("/loginOut")
    public Result loginOut() {
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            throw new BizException("您暂未登录");
        }
        SecurityUtils.getSubject().logout();
        return Result.success("注销成功");
    }

}

