package com.mq.music.mapper;

import com.mq.music.bean.Tuijian;
import com.mq.music.bean.TuijianExample;
import com.mq.music.vo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TuijianMapper {
    long countByExample(TuijianExample example);

    int deleteByExample(TuijianExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tuijian record);

    int insertSelective(Tuijian record);

    List<Tuijian> selectByExample(TuijianExample example);

    Tuijian selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tuijian record, @Param("example") TuijianExample example);

    int updateByExample(@Param("record") Tuijian record, @Param("example") TuijianExample example);

    int updateByPrimaryKeySelective(Tuijian record);

    int updateByPrimaryKey(Tuijian record);

    List<Tuijian> queryList(Map paramMap);

    Integer queryCount(Map paramMap);

    int deleteTuiJianBatch(Data data);

    List<Tuijian> queryTuiJianMusicList(Map paramMap);

    Integer queryTuiJianMusicCount(Map paramMap);
}