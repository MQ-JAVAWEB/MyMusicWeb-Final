package com.mq.music.service.impl;

import com.mq.music.bean.Manager;
import com.mq.music.bean.Singer;
import com.mq.music.mapper.SingerMapper;
import com.mq.music.service.SingerService;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public Page queryPage(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Manager> datas = singerMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize = singerMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public int deleteSinger(Integer id) {
        return singerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBachSinger(Data data) {
        return singerMapper.deleteBachSinger(data);
    }

    @Override
    public int saveSinger(Singer singer) {
        return singerMapper.insert(singer);
    }

    @Override
    public Singer getSingerPictureByName(String singerName) {
        return singerMapper.getSingerPictureByName(singerName);
    }

    @Override
    public Singer querySingerById(Integer id) {
        return singerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Singer getSingerById(Integer id) {
        return singerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateSinger(Singer singer) {
        return singerMapper.updateByPrimaryKey(singer);
    }

}
