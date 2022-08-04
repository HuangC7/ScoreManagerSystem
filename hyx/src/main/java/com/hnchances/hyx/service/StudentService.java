package com.hnchances.hyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnchances.hyx.entity.Student;

/**
 * (Student)表服务接口
 *
 */
public interface StudentService extends IService<Student> {
    /**
     * 根据学生姓名获取学生用户信息
     *
     * @param studentName
     * @return Student
     */
    Student getStudentByStudentName(String studentName);
}

