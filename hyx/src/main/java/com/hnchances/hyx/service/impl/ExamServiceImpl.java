package com.hnchances.hyx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnchances.hyx.dao.ExamDao;
import com.hnchances.hyx.entity.Exam;
import com.hnchances.hyx.service.ExamService;
import org.springframework.stereotype.Service;

/**
 * (Exam)表服务实现类
 *
 */
@Service("examService")
public class ExamServiceImpl extends ServiceImpl<ExamDao, Exam> implements ExamService {

}

