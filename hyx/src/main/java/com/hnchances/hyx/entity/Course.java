package com.hnchances.hyx.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Course)表实体类
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "课程")
@SuppressWarnings("serial")
public class Course extends Model<Course> implements Serializable {
    private static final long serialVersionUID = 634181537498893180L;
            
    @ApiModelProperty("id,key")
    private Integer id;
            
    @ApiModelProperty("课程名称")
    private String coursename;
            
    @ApiModelProperty("0正常，1删除")
    private Integer status;
}

