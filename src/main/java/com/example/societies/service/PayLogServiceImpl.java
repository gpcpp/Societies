package com.example.societies.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.societies.mapper.PayLogMapper;
import com.example.societies.pojo.PayLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService{

    @Autowired
    private PayLogMapper payLogMapper;

    public void add(PayLog payLog)
    {

        payLogMapper.insert(payLog);
    }

    public void updata(PayLog payLog)
    {
        payLogMapper.updateById(payLog);
    }

    public void delete(String id)
    {
        payLogMapper.deleteById(id);
    }

}
