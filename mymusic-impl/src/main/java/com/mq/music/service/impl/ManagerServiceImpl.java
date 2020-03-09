package com.mq.music.service.impl;

import com.mq.music.Exception.LoginFailException;
import com.mq.music.bean.Manager;
import com.mq.music.mapper.ManagerMapper;
import com.mq.music.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

//    @Override
//    public List<Messager> getAllManager() {
//        List<Messager> list=messagerMapper.selectAll();
//        return  list;
//    }

    @Override
    public Manager queryManagerLogin(Map<String, Object> paramMap) {
        Manager manager=managerMapper.queryManagerLogin(paramMap);

        if (manager==null) {
            throw  new LoginFailException("用户名或密码错误");

        }
        return manager;
    }
}
