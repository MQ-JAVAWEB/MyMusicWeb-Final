package com.mq.music.service;

import com.mq.music.bean.Manager;
import com.mq.music.util.Page;

import java.util.Map;

public interface ManagerService {
   // List<Messager> getAllManager();

    Manager queryManagerLogin(Map<String, Object> paramMap);

    Page queryPage(Map paramMap);

    int savaManager(Manager manager);
}
