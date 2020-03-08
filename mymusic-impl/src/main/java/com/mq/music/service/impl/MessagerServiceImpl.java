package com.mq.music.service.impl;

import com.mq.music.Exception.LoginFailException;
import com.mq.music.bean.Messager;
import com.mq.music.mapper.MessagerMapper;
import com.mq.music.service.MessagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessagerServiceImpl implements MessagerService {

    @Autowired
    private MessagerMapper messagerMapper;

    @Override
    public List<Messager> getAllManager() {
        List<Messager> list=messagerMapper.selectAll();
        return  list;
    }

    @Override
    public Messager queryMessagerLogin(Map<String, Object> paramMap) {
        Messager messager=messagerMapper.queryMessagerLogin(paramMap);

        if (messager==null) {
            throw  new LoginFailException("用户名或密码错误");

        }
        return messager;
    }
}
