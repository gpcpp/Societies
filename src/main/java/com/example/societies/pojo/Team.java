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
@Table(name = "team")
@ApiModel(value = "team", description = "社团表")
public class Team {
    @Id
    @TableId(value ="id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "社团编号")
    private Long id;

    @TableField(value = "name")
    @ApiModelProperty(value ="社团名字")
    @JsonProperty("name")
    private String name;

    @TableField(value = "total")
    @ApiModelProperty(value ="社团人数")
    @JsonProperty("total")
    private int total;


    @TableField(value = "manager")
    @ApiModelProperty(value ="社团团长")
    @JsonProperty("manager")
    private Long manager;

    @TableField(value = "manager_name")
    @ApiModelProperty(value ="社团团长")
    @JsonProperty("managerName")
    private String manager_name;

    @TableField(value = "create_time")
    @ApiModelProperty(value ="社团创建时间")
    @JsonProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime create_time;

    @TableField(value = "type_id")
    @ApiModelProperty(value ="社团类型编号")
    @JsonProperty("typeId")
    private Long type_id;

    @TableField(value = "type_name")
    @ApiModelProperty(value ="社团类型")
    @JsonProperty("typeName")
    private String type_name;
}
