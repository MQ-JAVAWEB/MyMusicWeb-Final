package com.mq.music.mapper;

import com.mq.music.bean.SingerType;
import com.mq.music.bean.SingerTypeExample;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SingerTypeMapper {
    long countByExample(SingerTypeExample example);

    int deleteByExample(SingerTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingerType record);

    int insertSelective(SingerType record);

    List<SingerType> selectByExample(SingerTypeExample example);

    SingerType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@RequestParam("record") SingerType record, @RequestParam("example") SingerTypeExample example);

    int updateByExample(@RequestParam("record") SingerType record, @RequestParam("example") SingerTypeExample example);

    int updateByPrimaryKeySelective(SingerType record);

    int updateByPrimaryKey(SingerType record);
}