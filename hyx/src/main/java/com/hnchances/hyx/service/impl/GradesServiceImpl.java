package com.hnchances.hyx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnchances.hyx.dao.GradesDao;
import com.hnchances.hyx.entity.Grades;
import com.hnchances.hyx.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Grades)表服务实现类
 *
 */
@Service("gradesService")
public class GradesServiceImpl extends ServiceImpl<GradesDao, Grades> implements GradesService {

    @Override
    public int deleteGradesById(Grades grades) {
        //采用mybatis—plus
        grades.setStatus(0);
        return this.baseMapper.updateById(grades);
    }
}

