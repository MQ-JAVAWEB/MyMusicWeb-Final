package com.mq.music.mapper;

import com.mq.music.bean.Manager;
import com.mq.music.bean.ManagerExample;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface ManagerMapper {
    long countByExample(ManagerExample example);

    int deleteByExample(ManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@RequestParam("record") Manager record, @RequestParam("example") ManagerExample example);

    int updateByExample(@RequestParam("record") Manager record, @RequestParam("example") ManagerExample example);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    
    Manager queryManagerLogin(Map<String, Object> paramMap);
}