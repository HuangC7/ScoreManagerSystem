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

    @Override
    public User getUserByUserName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("userName",username).eq("status",0);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public int deleteUserById(User user) {
        //采用mybatis—plus
        user.setStatus(0);
        return this.baseMapper.updateById(user);
    }
}

