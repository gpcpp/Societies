package com.example.societies.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.societies.mapper.MemberMapper;
import com.example.societies.pojo.Member;
import com.example.societies.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService{

    @Autowired
    private MemberMapper memberMapper;

    public void add(Member member)
    {
        memberMapper.insert(member);
    }

    public void updata(Member member)
    {
        memberMapper.updateById(member);
    }

    public void delete(String id)
    {
        memberMapper.deleteById(id);
    }

    public IPage<Member> selectMemberPage(Page<Member> page) {
        return memberMapper.selectMemberPage(page);
    }
}
