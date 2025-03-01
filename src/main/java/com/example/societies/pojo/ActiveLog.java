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
import java.util.Date;
@Data
@TableName("active_log")
@ApiModel(value = "ActiveLog", description = "报名记录表")
public class ActiveLog {
    @ApiModelProperty(value = "报名编号")
    @Id
    @TableId(value ="id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "报名时间")
    @TableField(value = "create_time")
    @JsonProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "活动编号")
    @TableField(value = "active_id")
    @JsonProperty("activeId")
    private Long active_id;

    @ApiModelProperty(value = "报名用户")
    @TableField(value = "user_name")
    @JsonProperty("userName")
    private String user_name;

    @ApiModelProperty(value = "报名用户电话")
    @TableField(value = "user_phone")
    @JsonProperty("userPhone")
    private String user_phone;

}
