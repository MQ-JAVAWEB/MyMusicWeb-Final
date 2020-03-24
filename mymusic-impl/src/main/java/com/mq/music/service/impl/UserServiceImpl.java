package com.mq.music.service.impl;

import com.mq.music.Exception.LoginFailException;
import com.mq.music.bean.User;
import com.mq.music.mapper.UserMapper;
import com.mq.music.service.UserService;
import com.mq.music.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public Page queryPageUser(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<User> datas = userMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize = userMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public User queryUserLogin(Map<String, Object> paramMap) {
        User user=userMapper.queryUserLogin(paramMap);
        if (user == null) {
            throw new LoginFailException("用户名或密码错误");

        }
        return user;
    }
}
