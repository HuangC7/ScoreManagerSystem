package com.hnchances.hyx.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)表实体类
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "用户")
@SuppressWarnings("serial")
@JsonIgnoreProperties(allowSetters = true, value = {"password"})
public class User extends Model<User> implements Serializable {
    private static final long serialVersionUID = 567114563600854604L;
            
    @ApiModelProperty("id自增，key")
    private Long id;
            
    @ApiModelProperty("密码，md5")
    private String password;
            
    @ApiModelProperty("用户名")
    private String username;
            
    @ApiModelProperty("电话号码11位")
    private String phone;
            
    @ApiModelProperty("真实姓名")
    private String truename;
            
    @ApiModelProperty("0-学生，1-老师，2- 领导或管理（可看所有班级）")
    private Integer identity;
            
    @ApiModelProperty("班级关联ID")
    private Integer classid;
            
    @ApiModelProperty("课程ID")
    private Integer courseid;
            
    @ApiModelProperty("学生ID")
    private Integer studentid;
            
    @ApiModelProperty("注册日期")
    private Date creatdatetime;
            
    @ApiModelProperty("修改日期")
    private Date updatedatetime;
            
    @ApiModelProperty("最后登录时间")
    private Date lastlogindatetime;
            
    @ApiModelProperty("ipv4")
    private String loginip;
            
    @ApiModelProperty("修改人ID")
    private Long updatebyid;
            
    @ApiModelProperty("0-正常，1-删除")
    private Integer status;
            
    @ApiModelProperty("0-未审核,1-审核")
    private Integer verify;
}

