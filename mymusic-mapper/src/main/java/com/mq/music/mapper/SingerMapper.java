package com.mq.music.mapper;

import com.mq.music.bean.Manager;
import com.mq.music.bean.Singer;
import com.mq.music.bean.SingerExample;
import com.mq.music.vo.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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

    List<Manager> queryList(Map paramMap);

    Integer queryCount(Map paramMap);

    int deleteBachSinger(Data data);

    Singer getSingerPictureByName(String singerName);
}