package com.hnchances.hyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnchances.hyx.dao.UserDao;
import com.hnchances.hyx.entity.User;
import com.hnchances.hyx.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

//    @Override
//    public String login(String username, String password, String code, HttpSession session, HttpServletResponse response, Model model) {
//        //在校验登陆之前先检验验证码的正确性
//        String realAuthCode = (String) session.getAttribute("code");
//        if(!realAuthCode.equalsIgnoreCase(code)) {
//            model.addAttribute("msg","验证码错误!");
//            return "login";
//        }
//        //1.获取 Subject
//        Subject subject = SecurityUtils.getSubject();
//        //2.封装 token
//        UsernamePasswordToken shiroToken = new UsernamePasswordToken(username, password);
//        //3.调用 subject.login(token) 方法
//        try {
//            subject.login(shiroToken);
//            //登录成功之后返回 token 给客户端
//            Map<String,String> map = new HashMap<String, String>();
//            map.put("username",username);
//            String token = JWTutil.getToken(map);  //登陆成功，生成JWT token
//            response.setHeader("token",token); // 将 token 设置在 header 里面
//            model.addAttribute("token",token);
//        }catch (UnknownAccountException e1) {
//            //用户名不存在
//            model.addAttribute("msg","用户名不存在!");
//            return "/login";
//        }catch (IncorrectCredentialsException e2) {
//            model.addAttribute("msg","密码错误!");
//            return "/login";
//        }
//        return "/index";
//    }


    @Override
    public User getUserByUserName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("userName",username);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public int deleteUserById(User user) {
        //采用mybatis—plus
        user.setStatus(0);
        return this.baseMapper.updateById(user);
    }
}

