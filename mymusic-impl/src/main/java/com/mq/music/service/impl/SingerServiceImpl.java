package com.mq.music.service.impl;

import com.mq.music.bean.Manager;
import com.mq.music.mapper.SingerMapper;
import com.mq.music.service.SingerService;
import com.mq.music.util.Page;
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
        Page page=new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));

        Integer startIndex=page.getStartIndex();
        paramMap.put("startIndex", startIndex);


        List<Manager> datas=singerMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize=singerMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }
}
