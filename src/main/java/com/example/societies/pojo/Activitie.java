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
@TableName("activitie")
@ApiModel(value = "Activitie", description = "活动表")
public class Activitie
{
    @Id
    @TableId(value ="id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "活动id")
    private Long id;

    @ApiModelProperty(value = "活动名称")
    @JsonProperty("name")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "活动概述")
    @JsonProperty("comm")
    @TableField(value = "comm")
    private String comm;

    @ApiModelProperty(value = "活动详情")
    @TableField(value = "detail")
    @JsonProperty("detail")
    private String detail;

    @ApiModelProperty(value = "活动要求")
    @JsonProperty("ask")
    @TableField(value = "ask")
    private String ask;

    @ApiModelProperty(value = "报名人数")
    @JsonProperty("total")
    @TableField(value = "total")
    private int total;

    @ApiModelProperty(value = "活动时间")
    @JsonProperty("activeTime")
    @TableField(value = "active_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime active_time;

    @ApiModelProperty(value = "发布社团id")
    @JsonProperty("teamId")
    @TableField(value = "team_id")
    private Long team_id;

    @ApiModelProperty(value = "发布社团名字")
    @JsonProperty("teamName")
    @TableField(value = "team_name")
    private String team_name;
}
