package com.hnchances.hyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnchances.hyx.dao.ExamDao;
import com.hnchances.hyx.entity.Exam;
import com.hnchances.hyx.entity.User;
import com.hnchances.hyx.service.ExamService;
import org.springframework.stereotype.Service;

/**
 * (Exam)表服务实现类
 *
 */
@Service("examService")
public class ExamServiceImpl extends ServiceImpl<ExamDao, Exam> implements ExamService {

    @Override
    public Exam getExamByExamName(String examName) {
        QueryWrapper<Exam> wrapper = new QueryWrapper<Exam>();
        wrapper.eq("examName",examName).eq("status",0);;
        return this.baseMapper.selectOne(wrapper);
    }
}

