package com.hnchances.hyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnchances.hyx.entity.Exam;
import com.hnchances.hyx.entity.User;

/**
 * (Exam)表服务接口
 *
 */
public interface ExamService extends IService<Exam> {
    /**
     * 根据考试名称获取考试信息
     *
     * @param examName
     * @return Exam
     */
    Exam getExamByExamName(String examName);
}

