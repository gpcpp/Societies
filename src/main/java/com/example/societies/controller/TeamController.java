package com.example.societies.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.Team;
import com.example.societies.pojo.Team;
import com.example.societies.pojo.TeamType;
import com.example.societies.pojo.User;
import com.example.societies.service.TeamServiceImpl;
import com.example.societies.service.TeamTypeServiceImpl;
import com.example.societies.service.UserServiceImpl;
import com.example.societies.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/association")
@Api(value = "社团管理",tags = "获取社团接口")
public class TeamController {
    @Autowired
    private TeamServiceImpl teamServiceImpl;
    @Autowired
    private TeamTypeServiceImpl teamTypeServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @ApiOperation(value = "社团添加")
    @PostMapping("/teams/add")
    public R register(@RequestBody Team team) {
        Long typeId = team.getType_id();
        Long manager = team.getManager();
        TeamType teamType = teamTypeServiceImpl.getById(typeId);
        User user = userServiceImpl.getById(manager);
        team.setManager_name(user.getName());
        team.setType_name(teamType.getName());
        teamServiceImpl.add(team);
        return new R("0", "添加成功","");
    }

    @ApiOperation(value = "社团删除")
    @PostMapping("/teams/del")
    public R delete(@RequestBody Map<String,String> params) {
        // 调用业务逻辑进行用户删除
        String id = params.get("id");
        teamServiceImpl.delete(id);
        return new R("0","删除成功","");
    }

    @ApiOperation(value = "社团修改")
    @PostMapping("/teams/upd")
    public R updata(@RequestBody Team team) {
        // 调用业务逻辑进行用户删除
        teamServiceImpl.updata(team);
        return new R("0","修改成功","");
    }



    @ApiModelProperty(value = "查询所有社团")
    @GetMapping("/teams/all")
    public R all_team(){
        return teamServiceImpl.all_team();
    }

    @ApiOperation(value = "获取管理社团")
    @GetMapping("/teams/man")
    public R getManTeamList(@RequestParam("manId")String id)
    {
        return teamServiceImpl.getManTeamList(id);
    }


    @ApiOperation(value = "社团分页查询")
    @GetMapping("/teams/page")
    public R pagelist(
            @RequestParam(defaultValue = "1",name = "pageIndex") String pageIndex,
            @RequestParam(defaultValue = "10",name = "pageSize") String pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String typeId)
    {
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();

        // 动态添加条件：仅在参数非空时添加
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (StringUtils.isNotBlank(typeId)) {
            queryWrapper.eq("type_id", typeId);
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
        Page<Team> page = new Page<>(index,size);
        IPage<Team> resultPage = teamServiceImpl.page(page, queryWrapper);
        // 返回封装结果
        return new R("0","查询成功",resultPage);
    }
}
