package com.example.societies.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.societies.mapper.TeamTypeMapper;
import com.example.societies.pojo.TeamType;
import com.example.societies.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeamTypeServiceImpl extends ServiceImpl<TeamTypeMapper, TeamType> implements TeamTypeService{

    @Autowired
    private TeamTypeMapper teamtypeMapper;

    public void add(TeamType teamtype)
    {
        teamtype.setCreate_time(LocalDateTime.now());
        teamtypeMapper.insert(teamtype);
    }

    public void updata(TeamType teamtype)
    {
        teamtypeMapper.updateById(teamtype);
    }

    public void delete(String id)
    {
        teamtypeMapper.deleteById(id);
    }


    public R all_teamtype()
    {
        List<TeamType> teamTypes = teamtypeMapper.selectList(null);
        return new R("0", "查询成功", teamTypes);
    }
}
