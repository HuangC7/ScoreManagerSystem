package com.hnchances.hyx.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * (Student)表实体类
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "学生")
@SuppressWarnings("serial")
public class Student extends Model<Student> implements Serializable {
    private static final long serialVersionUID = -24126985370582490L;
            
    @ApiModelProperty("id,key")
    private Long id;
            
    @ApiModelProperty("学生姓名")
    private String name;
            
    @ApiModelProperty("出生年月日")
    private Date birthday;
            
    @ApiModelProperty("身份证")
    private String idcard;
            
    @ApiModelProperty("班级ID")
    private Integer classid;
            
    @ApiModelProperty("0-正常，1-删除")
    private Integer status;
}

