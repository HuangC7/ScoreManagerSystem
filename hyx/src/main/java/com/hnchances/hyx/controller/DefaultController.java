package com.hnchances.hyx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hnchances.hyx.Exception.BizException;
import com.hnchances.hyx.aliyun.SmsUtil;
import com.hnchances.hyx.commom.Result;
import com.hnchances.hyx.entity.Class;
import com.hnchances.hyx.entity.Course;
import com.hnchances.hyx.entity.Student;
import com.hnchances.hyx.entity.User;
import com.hnchances.hyx.security.constant.SystemConstant;
import com.hnchances.hyx.security.util.JwtUtil;
import com.hnchances.hyx.service.ClassService;
import com.hnchances.hyx.service.CourseService;
import com.hnchances.hyx.service.StudentService;
import com.hnchances.hyx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * author: yuexuan_huang@126.com
 * Date: 2022/8/1
 * Description:登录和注册
 */

@RestController
@Api(value = "登录注册等",tags = "无需认证的相关接口")
public class DefaultController {
    @Resource
    private UserService userService;
    @Resource
    private StudentService studentService;
    @Resource
    private ClassService classService;
    @Resource
    private CourseService courseService;
    @Resource
    private StringRedisTemplate template;

    @ApiOperation(value = "注册学生", notes = "注册学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "phone", value = "手机", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", required = true),
            @ApiImplicitParam(name = "idCard", value = "学生身份证", required = true),
            @ApiImplicitParam(name = "name", value = "姓名", required = true),
            @ApiImplicitParam(name = "birthday", value = "出生年月", required = true)

    })
    @PostMapping("/register/student")
    public Result registerStudent(String username, String password, String phone ,String code,
                String idCard, String name,Date birthday) {
        Student student = studentService.getOne(new QueryWrapper<Student>().eq("idcard",idCard));
        if (student != null){
            ValueOperations<String, String> opsForValue = template.opsForValue();
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("phone", phone);
            if(userService.getOne(userQueryWrapper)!=null){
                throw new BizException("此手机号已注册");
            }
            if ((phone+code).equals(opsForValue.get(phone+code))) {
                // 通过shiro默认的加密工具类为注册用户的密码进行加密
                Object salt = ByteSource.Util.bytes(SystemConstant.JWT_SECRET_KEY);
                String md5 = new SimpleHash("MD5", password, salt, 1024).toHex();
                User user = new User(md5,username,phone,name,0,new Date(),0);
                user.setStudentid(student.getId().intValue());
                userService.save(user);
                student.setBirthday(birthday);
                studentService.updateById(student);
                return Result.success();
            }
            else{
                throw new BizException("验证码错误或失效");
            }
        }else {
            throw new BizException("该学生证号不存在");
        }

    }

    @ApiOperation(value = "注册老师", notes = "注册老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "phone", value = "手机", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", required = true),
            @ApiImplicitParam(name = "className", value = "班级", required = true),
            @ApiImplicitParam(name = "courseName", value = "课程", required = true),
            @ApiImplicitParam(name = "birthday", value = "出生年月", required = true)

    })
    @PostMapping("/register/teacher")
    public Result registerTeacher(String username, String password, String phone ,String code,
                                  String className,String courseName,String name,Date birthday) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", phone);
        if(userService.getOne(userQueryWrapper) != null){
            throw new BizException("此手机号已注册");
        }
        if ((phone+code).equals(opsForValue.get(phone+code))) {
            Class _class = classService.getOne(new QueryWrapper<Class>().eq("className",className));
            Course course = courseService.getOne(new QueryWrapper<Course>().eq("courseName",courseName));
            Object salt = ByteSource.Util.bytes(SystemConstant.JWT_SECRET_KEY);
            String md5 = new SimpleHash("MD5", password, salt, 1024).toHex();
            User user = new User(md5,username,phone,name,1,new Date(),0);
            user.setClassid(_class.getId());
            user.setCourseid(course.getId());
            userService.save(user);
            return Result.success();
        }
        else{
            throw new BizException("验证码错误或失效");
        }
    }

    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @PostMapping("/login")
    public Result login(String username, String password) {
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password, true);
            try {
                // shiro验证用户名密码
                SecurityUtils.getSubject().login(usernamePasswordToken);
                // 生成token
                String token = JwtUtil.createToken(username, false);
                // 将用户户名和token返回
                HashMap<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("Authorization", token);
                return Result.success(map);
            } catch (IncorrectCredentialsException e) {
                throw new BizException("登录密码错误");
            } catch (ExcessiveAttemptsException e) {
                throw new BizException("登录失败次数过多");
            } catch (LockedAccountException e) {
                throw new BizException("帐号已被锁定");
            } catch (DisabledAccountException e) {
                throw new BizException("帐号已被禁用");
            } catch (ExpiredCredentialsException e) {
                throw new BizException("帐号已过期");
            } catch (UnknownAccountException e) {
                throw new BizException("帐号不存在");
            } catch (UnauthorizedException e) {
                throw new BizException("您没有得到相应的授权");
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException("登录失败！！！");
            }
        }
        throw new BizException("你已经登录了");
    }

    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机",required = true)
    })
    @PostMapping("/isCode")
    public Result isCode(String phone) {
        //生成随机4位验证码
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
        try {
            String flag= SmsUtil.sendSms(phone, code);
        }catch (Throwable e){
            e.printStackTrace();
            throw new BizException("调用验证码接口失败!");
        }
        //使用手机号加验证码的方式，防止重复
        ValueOperations<String, String> opsForValue = template.opsForValue();
        opsForValue.set(phone+code, phone+code, 2, TimeUnit.MINUTES);
        if (opsForValue.get(phone+code) == null) {
            throw new BizException("存入redis失败!");
        }
        return Result.success();
    }


}
