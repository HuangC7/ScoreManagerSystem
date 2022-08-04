package com.hnchances.hyx.controller;


import com.google.common.collect.Lists;
import com.hnchances.hyx.Exception.BizException;
import com.hnchances.hyx.commom.Result;
import com.hnchances.hyx.entity.*;
import com.hnchances.hyx.entity.vo.GradeVo;
import com.hnchances.hyx.security.util.SecurityUtil;
import com.hnchances.hyx.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * (Grades)表控制层
 *
 */
@RestController
@Api(value = "成绩", tags = "成绩的相关接口")
@RequestMapping("/api/grades")
public class GradesController{

    /**
     * 服务对象
     */
    @Resource
    private GradesService gradesService;
    @Resource
    private ClassService classService;
    @Resource
    private StudentService studentService;
    @Resource
    private ExamService examService;

    @ApiOperation(value = "登记", notes = "老师登记自己课程的某次考试学生成绩信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grade", value = "成绩", required = true),
            @ApiImplicitParam(name = "studentName", value = "学生姓名", required = true),
            @ApiImplicitParam(name = "examName", value = "考试名称", required = true)
    })
    @PostMapping("/add")
    @RequiresPermissions({"roles:grades:add"})
    public Result addGrade(BigDecimal grade, String studentName,String examName) {
        User currentUser = SecurityUtil.getCurrentUser();
        //获取老师的班级id
        Integer classId = currentUser.getClassid();
        //获取老师的课程id
        String courseId = currentUser.getCourseid().toString();
        //获取学生id
        Long studentId = studentService.getStudentByStudentName(studentName).getId();
        //获取考试id
        Integer examid = examService.getExamByExamName(examName).getId();
        Grades grades = new Grades(courseId,examid,classId,grade,0,studentId);
        gradesService.save(grades);
        return Result.success();
    }

    @ApiOperation(value = "更新", notes = "更新成绩信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grades", value = "成绩实体", required = true)
    })
    @PostMapping("/updateById")
    @RequiresPermissions({"roles:grades:update"})
    public Result update(@RequestBody Grades grades) {
        gradesService.updateById(grades);
        return Result.success();
    }

    @ApiOperation(value = "删除", notes = "删除成绩信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grades", value = "成绩实体", required = true)
    })
    @PostMapping("/removeByIds")
    @RequiresPermissions({"roles:grades:del"})
    public Result removeById(@RequestBody Grades grades) {
        //软删除
        int delete = gradesService.deleteGradesById(grades);
        if(delete > 0){
            return Result.success();
        }else {
            throw new BizException("删除成绩信息失败");
        }
    }


    @ApiOperation(value = "根据学生姓名查询成绩", notes = "老师根据学生姓名查询成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentName", value = "学生姓名", required = true)
    })
    @GetMapping("/teacher/selectByStudentName")
    @RequiresPermissions({"roles:teacher:grades:select"})
    public Result selectByStudentName(String studentName) {
        User currentUser = SecurityUtil.getCurrentUser();
        //获取老师的班级名称
        String gradeClassName = classService.getById(currentUser.getClassid()).getClassname();
        List<GradeVo> allGradeVos = gradesService.selectByStudentName(studentName);
        List<GradeVo> gradeVos = Lists.newArrayList();
        for (GradeVo gradeVo : allGradeVos) {
            if (gradeVo.getClassName().equals(gradeClassName)){
                gradeVos.add(gradeVo);
            }
        }
        return Result.success(gradeVos);
    }

    @ApiOperation(value = "根据课程名称查询成绩", notes = "老师根据课程名称查询成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseName", value = "课程名称", required = true)
    })
    @GetMapping("/teacher/selectByCourseName")
    @RequiresPermissions({"roles:teacher:grades:select"})
    public Result selectByCourseName(String courseName) {
        User currentUser = SecurityUtil.getCurrentUser();
        //获取老师的班级名称
        String gradeClassName = classService.getById(currentUser.getClassid()).getClassname();
        List<GradeVo> allGradeVos = gradesService.selectByCourseName(courseName);
        List<GradeVo> gradeVos = Lists.newArrayList();
        for (GradeVo gradeVo : allGradeVos) {
            if (gradeVo.getClassName().equals(gradeClassName)){
                gradeVos.add(gradeVo);
            }
        }
        return Result.success(gradeVos);
    }

    @ApiOperation(value = "根据考试名称查询成绩", notes = "老师根据考试名称查询成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examName", value = "考试名称", required = true)
    })
    @GetMapping("/teacher/selectByExamName")
    @RequiresPermissions({"roles:teacher:grades:select"})
    public Result selectByExamName(String examName) {
        User currentUser = SecurityUtil.getCurrentUser();
        //获取老师的班级名称
        String gradeClassName = classService.getById(currentUser.getClassid()).getClassname();
        List<GradeVo> allGradeVos = gradesService.selectByExamName(examName);
        List<GradeVo> gradeVos = Lists.newArrayList();
        for (GradeVo gradeVo : allGradeVos) {
            if (gradeVo.getClassName().equals(gradeClassName)){
                gradeVos.add(gradeVo);
            }
        }
        return Result.success(gradeVos);
    }

    @ApiOperation(value = "根据班级名称查询成绩", notes = "管理员根据班级名称查询成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "className", value = "班级名称", required = true)
    })
    @GetMapping("/admin/selectByClassName")
    @RequiresPermissions({"roles:admin:grades:select"})
    public Result selectByClassName(String className) {
        List<GradeVo> gradeVos = gradesService.selectByClassName(className);
        return Result.success(gradeVos);
    }

}

