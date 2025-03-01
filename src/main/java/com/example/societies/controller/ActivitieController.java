package com.example.societies.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.Activitie;
import com.example.societies.pojo.Team;
import com.example.societies.service.ActivitieServiceImpl;
import com.example.societies.service.TeamServiceImpl;
import com.example.societies.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/association")
@Api(value = "活动管理",tags = "获取活动接口")
public class ActivitieController {
    @Autowired
    private ActivitieServiceImpl activitieServiceImpl;

    @Autowired
    private TeamServiceImpl teamServiceImpl;

    @ApiOperation(value = "活动添加")
    @PostMapping("/activities/add")
    public R register(@RequestBody Activitie activitie) {
        activitieServiceImpl.add(activitie);
        return new R("0", "添加成功","");
    }

    @ApiOperation(value = "活动删除")
    @PostMapping("/activities/del")
    public R delete(@RequestBody Map<String,String> params) {
        // 调用业务逻辑进行用户删除
        String id = params.get("id");
        activitieServiceImpl.delete(id);
        return new R("0","删除成功","");
    }

    @ApiOperation(value = "活动修改")
    @PostMapping("/activities/upd")
    public R updata(@RequestBody Activitie activitie) {
        // 调用业务逻辑进行用户删除
        activitieServiceImpl.updata(activitie);
        return new R("0","修改成功","");
    }

    @ApiOperation(value = "活动分页查询")
    @GetMapping("/activities/page")
    public R pagelist(
            @RequestParam(defaultValue = "1",name = "pageIndex") String pageIndex,
            @RequestParam(defaultValue = "10",name = "pageSize") String pageSize,
            @RequestParam(required = false) String teamName,
            @RequestParam(required = false) String activeName)
    {
        QueryWrapper<Activitie> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Team> teamqueryWrapper = new QueryWrapper<>();
        // 动态添加条件：仅在参数非空时添加
        if (StringUtils.isNotBlank(activeName)) {
            queryWrapper.eq("name",activeName);
        }

        if (StringUtils.isNotBlank(teamName)) {
            teamqueryWrapper.eq("name",teamName);
            Team team = teamServiceImpl.getOne(teamqueryWrapper);
            Long team_id = team.getId();
            queryWrapper.eq("team_id", team_id);
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
        Page<Activitie> page = new Page<>(index,size);
        IPage<Activitie> resultPage = activitieServiceImpl.page(page, queryWrapper);
        // 返回封装结果
        return new R("0","查询成功",resultPage);
    }
}
