package com.hnchances.hyx.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnchances.hyx.commom.Error;
import com.hnchances.hyx.commom.Result;
import com.hnchances.hyx.entity.Grades;
import com.hnchances.hyx.service.GradesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Grades)表控制层
 *
 */
@RestController
@Api(tags = "成绩")
@RequestMapping("/api/grades")
public class GradesController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private GradesService gradesService;

    @ApiOperation(value = "添加成绩信息")
    @PostMapping("/insert")
    @RequiresRoles({"1"})  //0-学生，1-老师，2- 领导或管理（可看所有班级）
    @RequiresPermissions({"roles:grades:add"})
    public Result save(@RequestBody Grades grades) {
        gradesService.save(grades);
        return Result.success();
    }

    @ApiOperation(value = "更新成绩信息")
    @PostMapping("/updateById")
    @RequiresRoles({"1"})  //0-学生，1-老师，2- 领导或管理（可看所有班级）
    @RequiresPermissions({"roles:grades:update"})
    public Result update(@RequestBody Grades grades) {
        gradesService.updateById(grades);
        return Result.success();
    }

    @ApiOperation(value = "删除成绩信息")
    @PostMapping("/removeByIds")
    @RequiresRoles({"1"})  //0-学生，1-老师，2- 领导或管理（可看所有班级）
    @RequiresPermissions({"roles:grades:del"})
    public Result removeById(@RequestBody Grades grades) {
        //软删除
        int delete = gradesService.deleteGradesById(grades);
        return delete > 0 ? Result.success() : Result.error(Error.ERROR_1);
    }


    @ApiOperation(value = "查询成绩信息")
    @GetMapping("/selectPage")
    @RequiresRoles({"1"})  //0-学生，1-老师，2- 领导或管理（可看所有班级）
    @RequiresPermissions({"roles:grades:select"})
    public Result selectPage(Page<Grades> page, Grades grades) {
        gradesService.page(page, new QueryWrapper<>(grades));
        return Result.success();
    }

}

