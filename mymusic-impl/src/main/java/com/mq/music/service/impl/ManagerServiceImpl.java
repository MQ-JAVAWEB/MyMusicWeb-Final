package com.mq.music.service.impl;

import com.mq.music.Exception.LoginFailException;
import com.mq.music.bean.Manager;
import com.mq.music.mapper.ManagerMapper;
import com.mq.music.service.ManagerService;
import com.mq.music.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public Page queryPage(Map paramMap) {
        Page page=new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));

        Integer startIndex=page.getStartIndex();
        paramMap.put("startIndex", startIndex);


        List<Manager> datas=managerMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize=managerMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public int savaManager(Manager manager) {

        return  managerMapper.insert(manager);
    }
}
