package com.mq.music.mapper;

import com.mq.music.bean.Messager;

import java.util.List;
import java.util.Map;

public interface MessagerMapper {
    List<Messager> selectAll();

    Messager queryMessagerLogin(Map<String, Object> paramMap);
}
