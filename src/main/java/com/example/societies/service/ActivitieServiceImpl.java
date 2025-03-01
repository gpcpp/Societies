package com.example.societies.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.societies.mapper.ActivitieMapper;
import com.example.societies.mapper.TeamMapper;
import com.example.societies.pojo.Activitie;
import com.example.societies.pojo.Team;
import com.example.societies.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitieServiceImpl extends ServiceImpl<ActivitieMapper, Activitie> implements ActivitieService{

    @Autowired
    private ActivitieMapper activitieMapper;
    @Autowired
    private TeamMapper teamMapper;

    public void add(Activitie activitie)
    {
        QueryWrapper<Team> queryWrapper = new QueryWrapper<Team>();
        queryWrapper.eq("id",activitie.getTeam_id());
        Team team = teamMapper.selectOne(queryWrapper);
        String name = team.getName();
        activitie.setTeam_name(name);
        activitieMapper.insert(activitie);
    }

    public void updata(Activitie activitie)
    {
        activitieMapper.updateById(activitie);
    }

    public void delete(String id)
    {
        activitieMapper.deleteById(id);
    }

}
