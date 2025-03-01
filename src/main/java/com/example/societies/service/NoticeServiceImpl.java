package com.example.societies.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.societies.mapper.NoticeMapper;
import com.example.societies.mapper.TeamMapper;
import com.example.societies.pojo.Notice;
import com.example.societies.pojo.Team;
import com.example.societies.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService{

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private TeamMapper teamMapper;

    public void add(Notice notice)
    {
        notice.setCreate_time(LocalDateTime.now());
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",notice.getTeam_id());
        Team team = teamMapper.selectOne(queryWrapper);
        String team_name = team.getName();
        notice.setTeam_name(team_name);
        noticeMapper.insert(notice);
    }

    public void updata(Notice notice)
    {
        noticeMapper.updateById(notice);
    }

    public void delete(String id)
    {
        noticeMapper.deleteById(id);
    }


    public R sysNotices(String token)
    {
        List<Notice> notices = noticeMapper.selectList(null);
        return new R("0", "查询成功", notices);
    }
}
