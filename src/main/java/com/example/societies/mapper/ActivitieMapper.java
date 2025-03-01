package com.example.societies.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.Activitie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivitieMapper extends BaseMapper<Activitie> {
    IPage<Activitie> selectActivitiePage(Page<Activitie> page);
}
