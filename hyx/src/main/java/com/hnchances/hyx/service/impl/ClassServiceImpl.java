package com.hnchances.hyx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnchances.hyx.dao.ClassDao;
import com.hnchances.hyx.entity.Class;
import com.hnchances.hyx.service.ClassService;
import org.springframework.stereotype.Service;

/**
 * (Class)表服务实现类
 *
 */
@Service("classService")
public class ClassServiceImpl extends ServiceImpl<ClassDao, Class> implements ClassService {

}

