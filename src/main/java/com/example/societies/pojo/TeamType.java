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
@Table(name = "teamtype")
@ApiModel(value = "TeamType",description = "社团类型")
public class TeamType {
    @ApiModelProperty(value = "社团类型id")
    @Id
    @TableId(value ="id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "社团类型名称")
    @TableField(value = "name")
    @JsonProperty(value = "name")
    private String name;

    @TableField(value = "create_time")
    @ApiModelProperty(value ="社团创建时间")
    @JsonProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime create_time;
}
