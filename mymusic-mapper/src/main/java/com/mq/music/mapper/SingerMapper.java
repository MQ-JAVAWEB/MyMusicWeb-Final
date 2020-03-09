package com.mq.music.mapper;

import com.mq.music.bean.Singer;
import com.mq.music.bean.SingerExample;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SingerMapper {
    long countByExample(SingerExample example);

    int deleteByExample(SingerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Singer record);

    int insertSelective(Singer record);

    List<Singer> selectByExample(SingerExample example);

    Singer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@RequestParam("record") Singer record, @RequestParam("example") SingerExample example);

    int updateByExample(@RequestParam("record") Singer record, @RequestParam("example") SingerExample example);

    int updateByPrimaryKeySelective(Singer record);

    int updateByPrimaryKey(Singer record);
}