package com.example.societies.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.ActiveLog;
import com.example.societies.pojo.User;
import com.example.societies.service.ActiveLogServiceImpl;
import com.example.societies.service.UserServiceImpl;
import com.example.societies.util.R;
import com.example.societies.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/association")
@Api(value = "报名日志管理",tags = "获取报名日志接口")
public class ActiveLogController {
    @Autowired
    private ActiveLogServiceImpl activeLogServiceImpl;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @ApiOperation(value = "报名日志添加")
    @PostMapping("/activeLogs/add")
    public R register(@RequestBody Map<String,String> prams) {
        String token = prams.get("token");
        String active_id = prams.get("activeId");
        Long active_lid = Long.parseLong(active_id);
        ActiveLog activeLog = new ActiveLog();
        activeLog.setActive_id(active_lid);
        activeLog.setCreate_time(LocalDateTime.now());
        //判断缓存数据是否存在
        boolean hasKey = redisUtil.hasKey(token);
        if (hasKey) {
            //存在，直接返回
            String json = (String) redisUtil.get(token);
            //hutool json转对象
            User user = JSONUtil.toBean(json, User.class);
            activeLog.setUser_name(user.getUsername());
            activeLog.setUser_phone(user.getPhone());
        }
        else
        {
            User user = userServiceImpl.getById(token);
            activeLog.setUser_name(user.getUsername());
            activeLog.setUser_phone(user.getPhone());
        }

        activeLogServiceImpl.add(activeLog);
        return new R("0", "添加成功","");
    }

    @ApiOperation(value = "报名日志删除")
    @PostMapping("/activeLogs/del")
    public R delete(@RequestBody Map<String,String> params) {
        // 调用业务逻辑进行用户删除
        String id = params.get("id");
        activeLogServiceImpl.delete(id);
        return new R("0","删除成功","");
    }

    @ApiOperation(value = "报名日志修改")
    @PostMapping("/activeLogs/upd")
    public R updata(@RequestBody ActiveLog activeLog) {
        // 调用业务逻辑进行用户删除
        activeLogServiceImpl.updata(activeLog);
        return new R("0","修改成功","");
    }

    @ApiOperation(value = "报名日志分页查询")
    @GetMapping("/activeLogs/page")
    public R pagelist(
            @RequestParam(defaultValue = "1",name = "pageIndex") Integer pageIndex,
            @RequestParam(defaultValue = "10",name = "pageSize") Integer pageSize,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String teamName)
    {
        QueryWrapper<ActiveLog> queryWrapper = new QueryWrapper<>();
        // 动态添加条件：仅在参数非空时添加
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.eq("user_name", userName);
        }
        if (StringUtils.isNotBlank(teamName)) {
            queryWrapper.eq("team_name", teamName);
        }
        // 调用分页查询方法
        Page<ActiveLog> page = new Page<>(pageIndex,pageSize);
        IPage<ActiveLog> resultPage = activeLogServiceImpl.page(page,queryWrapper);
        // 返回封装结果
        return new R("0","查询成功",resultPage);
    }

    @ApiOperation(value = "报名人员")
    @GetMapping("/activeLogs/list")
    public R getList(@RequestParam("activeId") String id)
    {
        QueryWrapper<ActiveLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("active_id",id);
        List<ActiveLog> activeLogs = activeLogServiceImpl.list(queryWrapper);
        return new R("0","查询成功",activeLogs);
    }
}
