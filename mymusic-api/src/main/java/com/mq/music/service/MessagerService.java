package com.mq.music.service;

import com.mq.music.bean.Messager;

import java.util.List;
import java.util.Map;

public interface MessagerService {
    List<Messager> getAllManager();

    Messager queryMessagerLogin(Map<String, Object> paramMap);
}
