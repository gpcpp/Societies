package com.example.societies.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.societies.mapper.TeamMapper;
import com.example.societies.pojo.Team;
import com.example.societies.pojo.User;
import com.example.societies.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService{


    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UserServiceImpl userServiceImpl;

    public void add(Team team)
    {
        team.setCreate_time(LocalDateTime.now());
        Long managerId =team.getManager();

        User user = userServiceImpl.getById(managerId);
        user.setType(1);
        userServiceImpl.updateById(user);
        teamMapper.insert(team);
    }

    public void updata(Team team)
    {
        teamMapper.updateById(team);
    }

    public void delete(String id)
    {
        Long idLong = Long.parseLong(id);
        teamMapper.deleteById(idLong);
    }

    public R all_team()
    {
        List<Team> teams = teamMapper.selectList(null);
        return new R("0", "查询成功", teams);
    }

    public R getManTeamList(String id)
    {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("manager",id);
        List<Team> teams = teamMapper.selectList(queryWrapper);
        return new R("0", "查询成功", teams);
    }


}
