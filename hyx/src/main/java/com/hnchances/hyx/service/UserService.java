package com.hnchances.hyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnchances.hyx.entity.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * (User)表服务接口
 *
 */
public interface UserService extends IService<User> {

     /**
       * 根据用户姓名获取用户信息
       *
       * @param username
       * @return User
       */
    User getUserByUserName(String username);

     /**
       * 删除用户信息(逻辑删除)
       *
       * @param user
       * @return int
       */
    int deleteUserById(User user);
}

