package com.hnchances.hyx.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Class)表实体类
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "班级")
@SuppressWarnings("serial")
public class Class extends Model<Class> implements Serializable {
    private static final long serialVersionUID = -42933818366029802L;
            
    @ApiModelProperty("id,key")
    private Integer id;
            
    @ApiModelProperty("班级名称")
    private String classname;
            
    @ApiModelProperty("0正常，1删除")
    private Integer status;
}

