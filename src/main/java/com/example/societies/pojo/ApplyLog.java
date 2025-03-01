package com.example.societies.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@TableName("apply_log")
@ApiModel(value = "ApplyLog", description = "申请记录表")
public class ApplyLog {
    @ApiModelProperty(value = "申请编号")
    @Id
    @TableId(value ="id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "社团名字")
    @TableField(value = "team_name")
    @JsonProperty("teamName")
    private String team_name;

    @ApiModelProperty(value = "申请用户名字")
    @TableField(value = "user_name")
    @JsonProperty("userName")
    private String user_name;

    @ApiModelProperty(value = "申请用户性别")
    @TableField(value = "user_gender")
    @JsonProperty("userGender")
    private String user_gender;

    @ApiModelProperty(value = "申请用户电话")
    @TableField(value = "user_phone")
    @JsonProperty("userPhone")
    private String user_phone;



    @TableField(value = "create_time")
    @ApiModelProperty(value ="社团创建时间")
    @JsonProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "申请状态")
    @TableField(value = "status")
    @JsonProperty("status")
    private int status;

}
