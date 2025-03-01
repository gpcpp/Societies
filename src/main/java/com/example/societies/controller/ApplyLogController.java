package com.example.societies.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.ApplyLog;
import com.example.societies.pojo.Member;
import com.example.societies.pojo.Team;
import com.example.societies.pojo.User;
import com.example.societies.service.ApplyLogServiceImpl;
import com.example.societies.service.MemberServiceImpl;
import com.example.societies.service.TeamServiceImpl;
import com.example.societies.service.UserServiceImpl;
import com.example.societies.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/association")
@Api(value = "申请日志管理",tags = "获取申请日志接口")
public class ApplyLogController {
    @Autowired
    private ApplyLogServiceImpl applylogServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private TeamServiceImpl teamServiceImpl;
    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @ApiOperation(value = "申请日志添加")
    @PostMapping("/applyLogs/add")
    public R register(@RequestBody Map<String,String> params) {
        //String id = params.get("id");
        String team_name = params.get("teamName");
        String token = params.get("token");
        ApplyLog applylog = new ApplyLog();
        //Long lid = Long.parseLong(id);
        //applylog.setId(lid);
        applylog.setStatus(0);
        applylog.setTeam_name(team_name);
        applylogServiceImpl.add(applylog,token);
        return new R("0", "添加成功","");
    }

    @ApiOperation(value = "申请日志删除")
    @PostMapping("/applyLogs/del")
    public R delete(@RequestBody Map<String,String> params) {
        // 调用业务逻辑进行用户删除
        String id = params.get("id");
        applylogServiceImpl.delete(id);
        return new R("0","删除成功","");
    }

    @ApiOperation(value = "申请日志修改")
    @PostMapping("/applyLogs/upd")
    public R updata(@RequestBody ApplyLog applylog) {
        System.out.println(applylog);
        // 调用业务逻辑进行用户删除
        if(applylog.getStatus()==1)
        {
            Member member = new Member();
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",applylog.getUser_name());
            User user = userServiceImpl.getOne(queryWrapper);
            member.setUser_gender(applylog.getUser_gender());
            member.setUser_phone(applylog.getUser_gender());
            member.setUser_age(user.getAge());
            member.setUser_id(user.getId());
            member.setUsername(applylog.getUser_name());
            member.setTeam_name(applylog.getTeam_name());
            member.setJoin_time(LocalDateTime.now());
            memberServiceImpl.add(member);
        }
        applylogServiceImpl.updata(applylog);
        return new R("0","修改成功","");
    }

    @ApiOperation(value = "申请日志分页查询")
    @GetMapping("/applyLogs/page")
    public R pagelist(
            @RequestParam(defaultValue = "1",name = "pageIndex") String pageIndex,
            @RequestParam(defaultValue = "10",name = "pageSize") String pageSize,
            @RequestParam(required = false) String token,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String teamName)
    {
        QueryWrapper<ApplyLog> queryWrapper = new QueryWrapper<>();
        // 动态添加条件：仅在参数非空时添加
        User user = userServiceImpl.getById(token);
        if(user.getType()==1)
        {
            Long id = user.getId();
            QueryWrapper<Team> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("manager",id);
            Team team = teamServiceImpl.getOne(queryWrapper1);
            queryWrapper.eq("team_name",team.getName());
        }
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.eq("user_name", userName);
        }
        if (StringUtils.isNotBlank(teamName)) {
            queryWrapper.eq("team_name", teamName);
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
        Page<ApplyLog> page = new Page<>(index,size);
        IPage<ApplyLog> resultPage = applylogServiceImpl.page(page, queryWrapper);
        // 返回封装结果
        return new R("0","查询成功",resultPage);
    }
}
