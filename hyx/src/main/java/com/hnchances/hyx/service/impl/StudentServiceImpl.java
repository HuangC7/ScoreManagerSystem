package com.hnchances.hyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnchances.hyx.dao.StudentDao;
import com.hnchances.hyx.entity.Exam;
import com.hnchances.hyx.entity.Student;
import com.hnchances.hyx.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * (Student)表服务实现类
 *
 */
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Override
    public Student getStudentByStudentName(String studentName) {
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>();
        wrapper.eq("name",studentName).eq("status",0);;
        return this.baseMapper.selectOne(wrapper);
    }
}

