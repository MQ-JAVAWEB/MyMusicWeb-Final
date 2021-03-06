package com.mq.music.service;

import com.mq.music.bean.Manager;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ManagerService {
    // List<Messager> getAllManager();

    Manager queryManagerLogin(Map<String, Object> paramMap);

    Page queryPage(Map paramMap);


    int savaManager(Manager manager);

    int deleteManager(Integer id);

    int deleteManagerBatch(Data data);



    Object modifyPassword(String password, String realname, HttpSession session);
}
