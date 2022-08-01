package com.hnchances.hyx.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Exam)表实体类
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "考试")
@SuppressWarnings("serial")
public class Exam extends Model<Exam> implements Serializable {
    private static final long serialVersionUID = -25183694114056664L;
            
    @ApiModelProperty("id,key")
    private Integer id;
            
    @ApiModelProperty("考试名称")
    private String examname;
            
    @ApiModelProperty("0正常1删除")
    private Integer status;
}

