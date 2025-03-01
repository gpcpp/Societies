package com.example.societies.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.PayLog;
import com.example.societies.pojo.Team;
import com.example.societies.pojo.User;
import com.example.societies.service.PayLogServiceImpl;
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
@Api(value = "支付日志管理",tags = "获取支付日志接口")
public class PayLogController {
    @Autowired
    private PayLogServiceImpl payLogServiceImpl;
    @Autowired
    private TeamServiceImpl teamServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @ApiOperation(value = "支付日志添加")
    @PostMapping("/payLogs/add")
    public R register(@RequestBody Map<String,String> prams) {
        String userId = prams.get("userId");
        String teamId = prams.get("teamId");
        String total = prams.get("total");
        PayLog payLog = new PayLog();
        Integer total1 = Integer.parseInt(total);
        Long userId1 = Long.parseLong(userId);
        Long teamId1 = Long.parseLong(teamId);
        QueryWrapper<Team> queryWrapper1 = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper1.eq("id",teamId1);
        queryWrapper2.eq("id",userId1);
        Team team = teamServiceImpl.getOne(queryWrapper1);
        User user = userServiceImpl.getOne(queryWrapper2);
        payLog.setTotal(total1);
        payLog.setTime(LocalDateTime.now());
        payLog.setTeam_name(team.getName());
        payLog.setUser_gender(user.getGender());
        payLog.setUser_name(user.getName());
        payLog.setUser_phone(user.getPhone());
        payLogServiceImpl.add(payLog);
        return new R("0", "添加成功","");
    }

    @ApiOperation(value = "支付日志删除")
    @PostMapping("/payLogs/del")
    public R delete(@RequestBody Map<String,String> params) {
        // 调用业务逻辑进行用户删除
        String id = params.get("id");
        payLogServiceImpl.delete(id);
        return new R("0","删除成功","");
    }

    @ApiOperation(value = "支付日志修改")
    @PostMapping("/payLogs/upd")
    public R updata(@RequestBody PayLog payLog) {
        // 调用业务逻辑进行用户删除
        payLogServiceImpl.updata(payLog);
        return new R("0","修改成功","");
    }

    @ApiOperation(value = "支付日志分页查询")
    @GetMapping("/payLogs/page")
    public R pagelist(
            @RequestParam(defaultValue = "1",name = "pageIndex") String pageIndex,
            @RequestParam(defaultValue = "10",name = "pageSize") String pageSize,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String teamName)
    {
        QueryWrapper<PayLog> queryWrapper = new QueryWrapper<>();
        // 动态添加条件：仅在参数非空时添加
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
        Page<PayLog> page = new Page<>(index,size);
        IPage<PayLog> resultPage = payLogServiceImpl.page(page, queryWrapper);
        // 返回封装结果
        return new R("0","查询成功",resultPage);
    }
}
