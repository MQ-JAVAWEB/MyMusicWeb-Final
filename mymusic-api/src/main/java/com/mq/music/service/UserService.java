package com.mq.music.service;

import com.mq.music.bean.User;
import com.mq.music.util.Page;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    Page queryPageUser(Map paramMap);

    User queryUserLogin(Map<String, Object> paramMap);

    int saveUser(User user);

    Object modifyMessage(User user, HttpSession session);

    int updateUser(User user);

    User getUserById(Integer id);
}
