package com.example.societies.service;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.societies.mapper.ApplyLogMapper;
import com.example.societies.pojo.ApplyLog;
import com.example.societies.pojo.User;
import com.example.societies.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplyLogServiceImpl extends ServiceImpl<ApplyLogMapper, ApplyLog> implements ApplyLogService{

    @Autowired
    private ApplyLogMapper applylogMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserServiceImpl userServiceImpl;

    public void add(ApplyLog applylog,String token)
    {
        applylog.setCreate_time(LocalDateTime.now());
        boolean hasKey = redisUtil.hasKey(token);
        if (hasKey) {
            //存在，直接返回
            String json = (String) redisUtil.get(token);
            //hutool json转对象
            User user = JSONUtil.toBean(json, User.class);
            applylog.setUser_name(user.getUsername());
            applylog.setUser_gender(user.getGender());
            applylog.setUser_phone(user.getPhone());
        }
        else
        {
            User user = userServiceImpl.getById(token);
            applylog.setUser_name(user.getUsername());
            applylog.setUser_gender(user.getGender());
            applylog.setUser_phone(user.getPhone());
        }
        applylogMapper.insert(applylog);
    }

    public void updata(ApplyLog applylog)
    {
        applylogMapper.updateById(applylog);
    }

    public void delete(String id)
    {
        applylogMapper.deleteById(id);
    }
}
