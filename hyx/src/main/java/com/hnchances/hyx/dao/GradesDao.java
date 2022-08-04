package com.hnchances.hyx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.hnchances.hyx.entity.Grades;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Grades)表数据库访问层
 *
 */
@Mapper
public interface GradesDao extends BaseMapper<Grades> {


    /**
     * 根据学生名称查询成绩
     */

    List<Grades> queryStudentGradeByStudentName(@Param("studentName")String studentName);

    /**
     * 根据课程名称查询成绩
     */
    List<Grades> queryStudentGradeByCourseName(@Param("courseName")String courseName);

    /**
     * 根据班级名称查询成绩
     */

    List<Grades> queryStudentGradeByClassName(@Param("className")String className);

    /**
     * 根据考试名称查询成绩
     */

    List<Grades> queryStudentGradeByExamName(@Param("examName")String examName);

}


