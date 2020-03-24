package com.mq.music.mapper;

import com.mq.music.bean.User;
import com.mq.music.bean.UserExample;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@RequestParam("record") User record, @RequestParam("example") UserExample example);

    int updateByExample(@RequestParam("record") User record, @RequestParam("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryList(Map paramMap);

    Integer queryCount(Map paramMap);

    User queryUserLogin(Map<String, Object> paramMap);
}