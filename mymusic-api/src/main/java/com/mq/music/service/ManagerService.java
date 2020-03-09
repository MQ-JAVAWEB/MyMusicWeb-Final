package com.mq.music.service;

import com.mq.music.bean.Manager;

import java.util.Map;

public interface ManagerService {
   // List<Messager> getAllManager();

    Manager queryManagerLogin(Map<String, Object> paramMap);
}
