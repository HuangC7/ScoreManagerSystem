package com.hnchances.hyx.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (Grades)表实体类
 * 
 * @author liuyzh
 * @since 2022-08-01 01:22:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "成绩")
@SuppressWarnings("serial")
public class Grades extends Model<Grades> implements Serializable {
    private static final long serialVersionUID = 652850706163195172L;
            
    @ApiModelProperty("id,key，自增")
    private Long id;
            
    @ApiModelProperty("课程关联id")
    private String courseid;
            
    @ApiModelProperty("考试关联id")
    private Integer examid;
            
    @ApiModelProperty("班级关联id")
    private Integer classid;
            
    @ApiModelProperty("成绩")
    private BigDecimal grades;
            
    @ApiModelProperty("0-正常，1-删除")
    private Integer status;
            
    @ApiModelProperty("学生关联id")
    private Long studentid;
}

