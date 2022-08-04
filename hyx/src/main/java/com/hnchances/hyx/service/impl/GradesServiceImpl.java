package com.hnchances.hyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hnchances.hyx.dao.*;
import com.hnchances.hyx.entity.*;
import com.hnchances.hyx.entity.Class;
import com.hnchances.hyx.entity.vo.GradeVo;
import com.hnchances.hyx.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Grades)表服务实现类
 *
 */
@Service("gradesService")
public class GradesServiceImpl extends ServiceImpl<GradesDao, Grades> implements GradesService {


    @Autowired
    private GradesDao gradeMapper;
    @Autowired
    private StudentDao studentMapper;
    @Autowired
    private ClassDao classMapper;
    @Autowired
    private CourseDao courseMapper;
    @Autowired
    private ExamDao examMapper;

    /**
     * 逻辑删除学生成绩
     *
     * @param grades
     */
    @Override
    public int deleteGradesById(Grades grades) {
        //采用mybatis—plus
        grades.setStatus(0);
        return gradeMapper.updateById(grades);
    }

    @Override
    public List<GradeVo> selectByStudentName(String studentName) {
        List<Grades> grades = queryStudentGradeByStudentName(studentName);
        List<GradeVo> studentGradeVos = Lists.newArrayList();
        for (Grades grade : grades) {
            Integer courseId = Integer.valueOf(grade.getCourseid());
            Integer classId = grade.getClassid();
            Integer examId = grade.getExamid();
            //获取课程名称
            Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("id",courseId).
                    eq("status",0));
            String gradeCourseName = course.getCoursename();
            //获取班级名称
            Class studentClass = classMapper.selectOne(new QueryWrapper<Class>().eq("id",classId).
                    eq("status",0));
            String gradeClassName = studentClass.getClassname();
            //获取考试名称
            Exam exam = examMapper.selectOne(new QueryWrapper<Exam>().eq("id",examId).
                    eq("status",0));
            String gradeExamName = exam.getExamname();
            //将查询出来的数据库表实体转换传到前端页面
            GradeVo gradeVo = new GradeVo(grade.getId().intValue(),studentName,gradeCourseName,
                    gradeClassName,gradeExamName,grade.getGrades().intValue(),grade.getStatus());
            //转换的添加集合
            studentGradeVos.add(gradeVo);
        }
        return studentGradeVos;
    }

    @Override
    public List<GradeVo> selectByCourseName(String courseName) {
        List<Grades> grades = queryStudentGradeByCourseName(courseName);
        List<GradeVo> studentGradeVos = Lists.newArrayList();
        for (Grades grade : grades) {
            Integer studentId = grade.getStudentid().intValue();
            Integer classId = grade.getClassid();
            Integer examId = grade.getExamid();
            //获取学生名字
            Student student = studentMapper.selectOne(new QueryWrapper<Student>().eq("id",studentId).
                    eq("status",0));
            String gradeStudentName = student.getName();
            //获取班级名称
            Class studentClass = classMapper.selectOne(new QueryWrapper<Class>().eq("id",classId).
                    eq("status",0));
            String gradeClassName = studentClass.getClassname();
            //获取考试名称
            Exam exam = examMapper.selectOne(new QueryWrapper<Exam>().eq("id",examId).
                    eq("status",0));
            String gradeExamName = exam.getExamname();
            //将查询出来的数据库表实体转换传到前端页面
            GradeVo gradeVo = new GradeVo(grade.getId().intValue(), gradeStudentName, courseName,
                    gradeClassName, gradeExamName, grade.getGrades().intValue(), grade.getStatus());
            //转换的添加集合
            studentGradeVos.add(gradeVo);
        }
        return studentGradeVos;
    }

    @Override
    public List<GradeVo> selectByClassName(String className) {
        List<Grades> grades = queryStudentGradeByClassName(className);
        List<GradeVo> studentGradeVos = Lists.newArrayList();
        for (Grades grade : grades) {
            Integer studentId = grade.getStudentid().intValue();
            Integer courseId = Integer.valueOf(grade.getCourseid());
            Integer examId = grade.getExamid();
            //获取学生名字
            Student student = studentMapper.selectOne(new QueryWrapper<Student>().eq("id",studentId).
                    eq("status",0));
            String gradeStudentName = student.getName();
            //获取课程名称
            Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("id",courseId).
                    eq("status",0));
            String gradeCourseName = course.getCoursename();
            //获取考试名称
            Exam exam = examMapper.selectOne(new QueryWrapper<Exam>().eq("id",examId).
                    eq("status",0));
            String gradeExamName = exam.getExamname();
            //将查询出来的数据库表实体转换传到前端页面
            GradeVo gradeVo = new GradeVo(grade.getId().intValue(),gradeStudentName,gradeCourseName,
                    className,gradeExamName,grade.getGrades().intValue(),grade.getStatus());
            //转换的添加集合
            studentGradeVos.add(gradeVo);
        }
        return studentGradeVos;
    }

    @Override
    public List<GradeVo> selectByExamName(String examName) {
        List<Grades> grades = queryStudentGradeByExamName(examName);
        List<GradeVo> studentGradeVos = Lists.newArrayList();
        for (Grades grade : grades) {
            Integer studentId = grade.getStudentid().intValue();
            Integer courseId = Integer.valueOf(grade.getCourseid());
            Integer classId = grade.getClassid();
            //获取学生名字
            Student student = studentMapper.selectOne(new QueryWrapper<Student>().eq("id",studentId).
                    eq("status",0));
            String gradeStudentName = student.getName();
            //获取课程名称
            Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("id",courseId).
                    eq("status",0));
            String gradeCourseName = course.getCoursename();
            //获取班级名称
            Class studentClass = classMapper.selectOne(new QueryWrapper<Class>().eq("id",classId).
                    eq("status",0));
            String gradeClassName = studentClass.getClassname();
            GradeVo gradeVo = new GradeVo(grade.getId().intValue(),gradeStudentName,gradeCourseName,
                    gradeClassName,examName,grade.getGrades().intValue(),grade.getStatus());
            //转换的添加集合
            studentGradeVos.add(gradeVo);
        }
        return studentGradeVos;
    }

    private List<Grades> queryStudentGradeByStudentName(String studentName) {
        return gradeMapper.queryStudentGradeByStudentName(studentName);
    }

    private List<Grades> queryStudentGradeByCourseName(String courseName) {
        return gradeMapper.queryStudentGradeByCourseName(courseName);
    }

    private List<Grades> queryStudentGradeByClassName(String className) {
        return gradeMapper.queryStudentGradeByClassName(className);
    }

    private List<Grades> queryStudentGradeByExamName(String examName) {
        return gradeMapper.queryStudentGradeByExamName(examName);
    }


}

