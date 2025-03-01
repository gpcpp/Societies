package com.example.societies.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.societies.pojo.Team;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper extends BaseMapper<Team> {
}
