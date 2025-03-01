package com.example.societies.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.Member;
import com.example.societies.pojo.Member;
import com.example.societies.pojo.Notice;
import com.example.societies.pojo.Team;
import com.example.societies.service.MemberServiceImpl;
import com.example.societies.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/association")
@Api(value = "成员管理",tags = "获取成员接口")
public class MemberController {
    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @ApiOperation(value = "成员添加")
    @PostMapping("/members/add")
    public R register(@RequestBody Member member) {
        memberServiceImpl.add(member);
        return new R("0", "添加成功","");
    }

    @ApiOperation(value = "成员删除")
    @PostMapping("/members/del")
    public R delete(@RequestBody Map<String,String> params) {
        // 调用业务逻辑进行用户删除
        String id = params.get("id");
        memberServiceImpl.delete(id);
        return new R("0","删除成功","");
    }

    @ApiOperation(value = "成员修改")
    @PostMapping("/members/upd")
    public R updata(@RequestBody Member member) {
        // 调用业务逻辑进行用户删除
        memberServiceImpl.updata(member);
        return new R("0","修改成功","");
    }

    @ApiOperation(value = "成员分页查询")
    @GetMapping("/members/page")
    public R pagelist(
            @RequestParam(defaultValue = "1",name = "pageIndex") String pageIndex,
            @RequestParam(defaultValue = "10",name = "pageSize") String pageSize,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String teamName)
    {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();

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
        Page<Member> page = new Page<>(index,size);
        IPage<Member> resultPage = memberServiceImpl.page(page, queryWrapper);
        // 返回封装结果
        return new R("0","查询成功",resultPage);
    }

}

