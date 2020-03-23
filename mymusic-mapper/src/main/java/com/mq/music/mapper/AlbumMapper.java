package com.mq.music.mapper;

import com.mq.music.bean.Album;
import com.mq.music.bean.AlbumExample;
import com.mq.music.vo.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface AlbumMapper {
    long countByExample(AlbumExample example);

    int deleteByExample(AlbumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Album record);

    int insertSelective(Album record);

    List<Album> selectByExample(AlbumExample example);

    Album selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@RequestParam("record") Album record, @RequestParam("example") AlbumExample example);

    int updateByExample(@RequestParam("record") Album record, @RequestParam("example") AlbumExample example);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);

    List<Album> queryList(Map paramMap);

    Integer queryCount(Map paramMap);

    int deleteBachAlbum(Data data);
}