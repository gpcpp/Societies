package com.example.societies.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.Notice;
import com.example.societies.service.NoticeServiceImpl;
import com.example.societies.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/association")
@Api(value = "公告管理",tags = "获取公告接口")
public class NoticeController {
    @Autowired
    private NoticeServiceImpl noticeServiceImpl;

    @ApiModelProperty(value = "系统通知")
    @GetMapping("/sys/notices")
    public R sysNotices(String token) {
        return noticeServiceImpl.sysNotices(token);
    }

    @ApiOperation(value = "公告添加")
    @PostMapping("/notices/add")
    public R register(@RequestBody Notice notice) {
        noticeServiceImpl.add(notice);
        return new R("0", "添加成功","");
    }

    @ApiOperation(value = "公告删除")
    @PostMapping("/notices/del")
    public R delete(@RequestBody Map<String,String> params) {
        // 调用业务逻辑进行用户删除
        String id = params.get("id");
        noticeServiceImpl.delete(id);
        return new R("0","删除成功","");
    }

    @ApiOperation(value = "公告修改")
    @PostMapping("/notices/upd")
    public R updata(@RequestBody Notice notice) {
        // 调用业务逻辑进行用户删除
        noticeServiceImpl.updata(notice);
        return new R("0","修改成功","");
    }


    @ApiOperation(value = "公告分页查询")
    @GetMapping("/notices/page")
    public R pagelist(
            @RequestParam(defaultValue = "1",name = "pageIndex") String pageIndex,
            @RequestParam(defaultValue = "10",name = "pageSize") String pageSize,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String teamName)
    {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();

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
        Page<Notice> page = new Page<>(index,size);
        IPage<Notice> resultPage = noticeServiceImpl.page(page, queryWrapper);
        // 返回封装结果
        return new R("0","查询成功",resultPage);
    }
}
