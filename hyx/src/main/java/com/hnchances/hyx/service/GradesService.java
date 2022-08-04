package com.hnchances.hyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnchances.hyx.entity.Grades;
import com.hnchances.hyx.entity.User;
import com.hnchances.hyx.entity.vo.GradeVo;

import java.util.List;

/**
 * (Grades)表服务接口
 *
 */
public interface GradesService extends IService<Grades> {


    /**
     * 逻辑删除学生成绩
     * */
    int deleteGradesById(Grades grades);

    /**
     * 根据学生名称查询成绩
     * */
    List<GradeVo> selectByStudentName(String studentName);
    /**
     * 根据课程名称查询成绩
     * */
    List<GradeVo> selectByCourseName(String courseName);
    /**
     * 根据班级名称查询成绩
     * */
    List<GradeVo> selectByClassName(String className);
    /**
     * 根据考试名称查询成绩
     * */
    List<GradeVo> selectByExamName(String examName);
}

