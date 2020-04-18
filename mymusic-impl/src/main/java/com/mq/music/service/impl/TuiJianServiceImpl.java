package com.mq.music.service.impl;

import com.mq.music.bean.Tuijian;
import com.mq.music.mapper.TuijianMapper;
import com.mq.music.service.TuiJianService;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TuiJianServiceImpl implements TuiJianService {

    @Autowired
    private TuijianMapper tuijianMapper;

    @Override
    public Page queryPage(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Tuijian> datas = tuijianMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize = tuijianMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public int deleteTuiJianBatch(Data data) {
        return tuijianMapper.deleteTuiJianBatch(data);
    }

    @Override
    public int deleteTuiJian(Integer id) {
        return tuijianMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page queryPageTuiJianMusic(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Tuijian> datas = tuijianMapper.queryTuiJianMusicList(paramMap);

        page.setDatas(datas);

        Integer totalsize = tuijianMapper.queryTuiJianMusicCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public Tuijian getSongNameById(Integer id) {
        return tuijianMapper.selectByPrimaryKey(id);
    }

}
