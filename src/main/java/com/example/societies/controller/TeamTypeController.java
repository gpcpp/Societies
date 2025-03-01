package com.example.societies.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.TeamType;
import com.example.societies.pojo.User;
import com.example.societies.service.TeamTypeServiceImpl;
import com.example.societies.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/association")
@Api(value = "社团类型管理",tags = "获取社团类型接口")
public class TeamTypeController {
    @Autowired
    private TeamTypeServiceImpl teamtypeServiceImpl;

    @ApiOperation(value = "社团类型添加")
    @PostMapping("/teamTypes/add")
    public R register(@RequestBody TeamType teamtype) {
        teamtypeServiceImpl.add(teamtype);
        return new R("0", "添加成功","");
    }

    @ApiOperation(value = "社团类型删除")
    @PostMapping("/teamTypes/del")
    public R delete(@RequestBody Map<String,String> params) {
        // 调用业务逻辑进行用户删除
        String id = params.get("id");
        teamtypeServiceImpl.delete(id);
        return new R("0","删除成功","");
    }

    @ApiOperation(value = "社团类型修改")
    @PostMapping("/teamTypes/upd")
    public R updata(@RequestBody TeamType teamtype) {
        // 调用业务逻辑进行用户删除
        teamtypeServiceImpl.updata(teamtype);
        return new R("0","修改成功","");
    }

    @ApiOperation(value = "社团类型分页查询")
    @GetMapping("/teamTypes/page")
    public R pagelist(
            @RequestParam(defaultValue = "1",name = "pageIndex") String pageIndex,
            @RequestParam(defaultValue = "10",name = "pageSize") String pageSize,
            @RequestParam(required = false) String name)
    {
        QueryWrapper<TeamType> queryWrapper = new QueryWrapper<>();
        // 动态添加条件：仅在参数非空时添加
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        long index =1,size=10;
        // 调用分页查询方法
        if(pageIndex!=null)
        {
            index = Long.parseLong(pageIndex);
        }
        if(pageSize!=null)
        {
            size = Long.parseLong(pageSize);
        }
        Page<TeamType> page = new Page<>(index,size);
        IPage<TeamType> resultPage = teamtypeServiceImpl.page(page, queryWrapper);
        // 返回封装结果
        return new R("0","查询成功",resultPage);
    }

    @ApiModelProperty(value = "查询所有社团类型")
    @GetMapping("/teamTypes/all")
    public R all_team(){
        return teamtypeServiceImpl.all_teamtype();
    }
}
