package com.example.societies.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.service.UserServiceImpl;
import com.example.societies.util.R;
import com.example.societies.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/association")
@Api(value = "用户管理",tags = "获取用户接口")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        return userServiceImpl.login(user);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/users/add")
    public R register(@RequestBody User user) {
        // 调用业务逻辑进行用户注册
        userServiceImpl.register(user);
        return new R("0", "添加成功","");
    }

    @ApiOperation(value = "用户删除")
    @PostMapping("/users/del")
    public R delete(@RequestBody Map<String,String> params) {
        // 调用业务逻辑进行用户删除
        String id = params.get("id");
        userServiceImpl.delete(id);
        return new R("0","删除成功","");
    }

    @ApiOperation(value = "用户修改")
    @PostMapping("/users/upd")
    public R updata(@RequestBody User user) {
        // 调用业务逻辑进行用户删除

        userServiceImpl.updata(user);
        return new R("0","修改成功","");
    }

    @ApiOperation(value = "用户修改")
    @PostMapping("/info")
    public R updinfo(@RequestBody User user) {
        // 调用业务逻辑进行用户删除
        userServiceImpl.updata(user);
        return new R("0","修改成功","");
    }



    @ApiOperation(value = "用户个人资料")
    @GetMapping("/info")
    public R info(String token)
    {
        return userServiceImpl.info(token);
    }

    @ApiOperation(value = "用户分页查询")
    @GetMapping("/users/page")
    public R pagelist(
            @RequestParam(defaultValue = "1",name = "pageIndex") Integer pageIndex,
            @RequestParam(defaultValue = "10",name = "pageSize") Integer pageSize,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 动态添加条件：仅在参数非空时添加
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.eq("username", userName);
        }
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (StringUtils.isNotBlank(phone)) {
            queryWrapper.eq("phone", phone);
        }
        Page<User> page = new Page<>(pageIndex,pageSize);
        IPage<User> resultPage = userServiceImpl.page(page, queryWrapper);
        // 返回封装结果
        return new R("0","查询成功",resultPage);
    }



    @ApiOperation(value = "修改密码")
    @PostMapping("/pwd")
    public R updPwd(@RequestBody Map<String, String> params) {
        String token = params.get("token");
        String newPwd = params.get("password");
        userServiceImpl.updPwd(newPwd, token);
        return new R("0", "修改成功", "");
    }


    @ApiOperation(value = "获取旧密码")
    @GetMapping("/checkPwd")
    public R checkPwd(@RequestParam("oldPwd")String pwd,String token)
    {
        return userServiceImpl.checkPwd(pwd,token);
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/exit")
    public R exit(String token)
    {
        return userServiceImpl.exit(token);
    }

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/users/getAll")
    public R getAll()
    {
        return userServiceImpl.getAll();
    }



}
