package com.example.societies.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.societies.mapper.ActiveLogMapper;
import com.example.societies.pojo.ActiveLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveLogServiceImpl extends ServiceImpl<ActiveLogMapper, ActiveLog> implements ActiveLogService{

    @Autowired
    private ActiveLogMapper activeLogMapper;

    public void add(ActiveLog activeLog)
    {
        activeLogMapper.insert(activeLog);
    }

    public void updata(ActiveLog activeLog)
    {
        activeLogMapper.updateById(activeLog);
    }

    public void delete(String id)
    {
        activeLogMapper.deleteById(id);
    }

}
