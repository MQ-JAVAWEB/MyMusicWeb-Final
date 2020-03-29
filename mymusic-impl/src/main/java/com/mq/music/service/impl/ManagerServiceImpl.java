package com.mq.music.service.impl;

import com.mq.music.Exception.LoginFailException;
import com.mq.music.bean.Manager;
import com.mq.music.mapper.ManagerMapper;
import com.mq.music.service.ManagerService;
import com.mq.music.util.Const;
import com.mq.music.util.MD5Util;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
        Manager manager = managerMapper.queryManagerLogin(paramMap);

        if (manager == null) {
            throw new LoginFailException("用户名或密码错误");

        }
        return manager;
    }

    @Override
    public Page queryPage(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Manager> datas = managerMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize = managerMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public int savaManager(Manager manager) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String createtime = sdf.format(date);
        manager.setCreatetime(createtime);
        manager.setPassword(MD5Util.digest(Const.PASSWORD));
        return managerMapper.insert(manager);
    }

    @Override
    public int deleteManager(Integer id) {
        return managerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteManagerBatch(Data data) {

        return managerMapper.deleteManagerBatch(data);
    }

    @Override
    public Object modifyPassword(String password,String username, HttpSession session) {
        Map<String,Object> map=new HashMap<String, Object>();
        if(password!=null){
            Manager manager=managerMapper.selectBypassword(username);
            Integer id=manager.getId();
            Manager m=new Manager();
            m.setId(id);
            m.setPassword(MD5Util.digest(password));
            return managerMapper.updateByPrimaryKeySelective(m);
            }
        return 0;
    }

}
