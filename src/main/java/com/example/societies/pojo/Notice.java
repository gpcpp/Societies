package com.example.societies.pojo;

import cn.hutool.core.date.DateTime;
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
@ApiModel(value = "notice", description = "公告")
@Table(name = "notice")
public class Notice {
    @Id
    @TableId(value ="id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "公告id")
    @JsonProperty("id")
    private Long id;

    @TableField("team_name")
    @ApiModelProperty(value = "团队")
    @JsonProperty("teamName")
    private String team_name;

    @TableField("team_id")
    @ApiModelProperty(value = "团队")
    @JsonProperty("teamId")
    private Long team_id;

    @TableField("title")
    @ApiModelProperty(value = "公告标题")
    @JsonProperty("title")
    private String title;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    @JsonProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime create_time;

    @TableField("detail")
    @ApiModelProperty(value = "公告详情")
    @JsonProperty("detail")
    private String detail;

}
