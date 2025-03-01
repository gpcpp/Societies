package com.example.societies.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "member")
@ApiModel(value = "member", description = "成员表")
public class Member {
    @Id
    @TableId(value ="id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "成员编号")
    private long id;

    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户编号")
    @JsonProperty("userId")
    private long user_id;

    @TableField(value = "user_name")
    @ApiModelProperty(value ="成员名字")
    @JsonProperty("userName")
    private String username;

    @TableField(value = "user_phone")
    @ApiModelProperty(value ="成员名字")
    @JsonProperty("userPhone")
    private String user_phone;

    @TableField(value = "user_gender")
    @ApiModelProperty(value ="成员名字")
    @JsonProperty("userGender")
    private String user_gender;

    @TableField(value = "team_name")
    @ApiModelProperty(value = "团队名字")
    @JsonProperty("teamName")
    private String team_name;

    @TableField(value = "user_age")
    @ApiModelProperty(value ="成员名字")
    @JsonProperty("userAge")
    private int user_age;

    @TableField(value = "join_time")
    @ApiModelProperty(value = "入团时间")
    @JsonProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime join_time;
}
