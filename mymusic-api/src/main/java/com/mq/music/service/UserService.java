package com.mq.music.service;

import com.mq.music.bean.User;
import com.mq.music.util.Page;

import java.util.Map;

public interface UserService {
    Page queryPageUser(Map paramMap);

    User queryUserLogin(Map<String, Object> paramMap);
}
