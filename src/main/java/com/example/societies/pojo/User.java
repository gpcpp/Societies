package com.example.societies.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@TableName(value = "user")
@Accessors(chain = true)
@NoArgsConstructor  //无参构造 必须添加
@AllArgsConstructor //全参构造
@ApiModel(value = "User", description = "用户表")
public class User {
    @Id
    @TableId(value ="id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户id")
    private Long id;

    @TableField(value = "username")
    @JsonProperty("userName")
    @ApiModelProperty(value = "用户账号")
    private String username;

    @TableField(value = "password")
    @JsonProperty("passWord")
    @ApiModelProperty(value = "用户密码")
    private String password;

    @TableField(value = "name")
    @JsonProperty("name")
    @ApiModelProperty(value = "用户名")
    private String name;

    @TableField(value = "gender")
    @JsonProperty("gender")
    @ApiModelProperty(value = "用户性别")
    private String gender;

    @TableField(value ="age")
    @JsonProperty("age")
    @ApiModelProperty(value = "用户年龄")
    private int age;

    @TableField(value = "phone")
    @JsonProperty("phone")
    @ApiModelProperty(value = "用户电话")
    private String phone;

    @TableField(value = "address")
    @JsonProperty("address")
    @ApiModelProperty(value = "用户地址")
    private String address;

    @TableField(value = "type")
    @JsonProperty("type")
    @ApiModelProperty(value = "用户类型")
    private int type;
}
