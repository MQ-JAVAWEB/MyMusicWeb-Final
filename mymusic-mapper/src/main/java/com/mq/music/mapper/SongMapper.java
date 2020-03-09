package com.mq.music.mapper;

import com.mq.music.bean.Song;
import com.mq.music.bean.SongExample;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SongMapper {
    long countByExample(SongExample example);

    int deleteByExample(SongExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Song record);

    int insertSelective(Song record);

    List<Song> selectByExample(SongExample example);

    Song selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@RequestParam("record") Song record, @RequestParam("example") SongExample example);

    int updateByExample(@RequestParam("record") Song record, @RequestParam("example") SongExample example);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKey(Song record);
}