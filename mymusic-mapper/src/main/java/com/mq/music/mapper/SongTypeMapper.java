package com.mq.music.mapper;

import com.mq.music.bean.SongType;
import com.mq.music.bean.SongTypeExample;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SongTypeMapper {
    long countByExample(SongTypeExample example);

    int deleteByExample(SongTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SongType record);

    int insertSelective(SongType record);

    List<SongType> selectByExample(SongTypeExample example);

    SongType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@RequestParam("record") SongType record, @RequestParam("example") SongTypeExample example);

    int updateByExample(@RequestParam("record") SongType record, @RequestParam("example") SongTypeExample example);

    int updateByPrimaryKeySelective(SongType record);

    int updateByPrimaryKey(SongType record);
}