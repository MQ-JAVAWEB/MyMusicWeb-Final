package com.mq.music.mapper;

import com.mq.music.bean.Song;
import com.mq.music.bean.SongExample;
import com.mq.music.vo.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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

    List<Song> queryList(Map paramMap);

    Integer queryCount(Map paramMap);

    int deleteBachMusic(Data data);

    List<Song> queryHotMusicList(Map paramMap);

    Integer queryHotMusicCount(Map paramMap);

    List<Song> queryNewMusicList(Map paramMap);

    Integer queryNewMusicCount(Map paramMap);

    List<Song> queryOriginalMusicList(Map paramMap);

    Integer queryOriginalMusicCount(Map paramMap);


}