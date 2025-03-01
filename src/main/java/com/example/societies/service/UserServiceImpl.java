package com.example.societies.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.json.JSONUtil;
import com.example.societies.mapper.UserMapper;
import com.example.societies.pojo.Team;
import com.example.societies.pojo.User;
import com.example.societies.util.R;
import com.example.societies.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;


    public R login(User user) {
        // 基于账号和密码查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        wrapper.eq(User::getPassword, user.getPassword());
        User one = this.getOne(wrapper);  // 避免多条记录时抛出异常
        if (one != null) {
            // 将 User 对象转为 JSON 字符串
            String jsonStr = JSONUtil.toJsonStr(one);
            // 保存到 Redis 中，以用户ID为 key
            redisUtil.set(one.getId().toString(), jsonStr);
            return new R("0", "登录成功", one.getId().toString());
        }
        return new R("-1", "登录失败", null);
    }

    public void register(User user) {
            userMapper.insert(user);
    }

    public void delete(String id)
    {
        userMapper.deleteById(id);
    }

    public void updata(User user)
    {
        userMapper.updateById(user);
    }

    public R info(String token) {
        //判断缓存数据是否存在
        boolean hasKey = redisUtil.hasKey(token);
        if (hasKey) {
            //存在，直接返回
            String json = (String) redisUtil.get(token);
            //hutool json转对象
            User user = JSONUtil.toBean(json, User.class);
            return new R("0", "查询成功", user);
        }
        //不在存在情况，查询数据库
        User byId = this.getById(token);
        if (byId == null) {
            return new R("-1", "用户不存在", null);
        }
        return new R("0", "查询成功", byId);
    }

    public R checkPwd(String pwd,String token)
    {
        boolean hasKey = redisUtil.hasKey(token);
        if (hasKey) {
            //存在，直接返回
            String json = (String) redisUtil.get(token);
            //hutool json转对象
            User user = JSONUtil.toBean(json, User.class);
            if(Objects.equals(user.getPassword(), pwd))
            {
                return new R("0","旧密码正确",null);
            }
            else
            {
                return new R("-1","旧密码错误",null);
            }
        }
        User byId = this.getById(token);
        if (byId == null) {
            return new R("-1", "用户不存在", null);
        }
        else
        {
            if(Objects.equals(byId.getPassword(), pwd))
            {
                return new R("0","旧密码正确",null);
            }
            else
            {
                return new R("-1","旧密码错误",null);
            }
        }
    }

    public void updPwd(String pwd,String token)
    {
        User user = this.getById(token);
        user.setPassword(pwd);
        userMapper.updateById(user);
    }

    public R getAll()
    {
        List<User> users = userMapper.selectList(null);
        return new R("0", "查询成功", users);
    }

    public R exit(String token)
    {
        return new R("0","退出成功","");
    }
}

