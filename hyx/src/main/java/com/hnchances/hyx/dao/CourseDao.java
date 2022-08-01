package com.hnchances.hyx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.hnchances.hyx.entity.Course;

/**
 * (Course)表数据库访问层
 *
 */
@Mapper
public interface CourseDao extends BaseMapper<Course> {

}


