package com.example.societies.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.PayLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {

}
