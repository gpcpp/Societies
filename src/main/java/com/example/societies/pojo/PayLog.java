package com.example.societies.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "缴费记录")
@Table(name = "pay_log")
public class PayLog {
    @ApiModelProperty(value = "报名编号")
    @Id
    @TableId(value ="id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "缴费金额")
    @TableField(value = "total")
    @JsonProperty(value = "total")
    private int total;

    @ApiModelProperty(value = "社团名字")
    @TableField(value = "team_name")
    @JsonProperty(value = "teamName")
    private String team_name;

    @ApiModelProperty(value = "报名编号")
    @TableField(value = "user_name")
    @JsonProperty(value = "userName")
    private String user_name;

    @ApiModelProperty(value = "报名编号")
    @TableField(value = "user_phone")
    @JsonProperty(value = "userPhone")
    private String user_phone;

    @ApiModelProperty(value = "报名编号")
    @TableField(value = "user_gender")
    @JsonProperty(value = "userGender")
    private String user_gender;

    @ApiModelProperty(value = "报名编号")
    @TableField(value = "create_time")
    @JsonProperty(value = "createTime")
    private LocalDateTime time;

}
