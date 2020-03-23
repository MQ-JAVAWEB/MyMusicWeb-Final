package com.mq.music.service.impl;

import com.mq.music.bean.Album;
import com.mq.music.mapper.AlbumMapper;
import com.mq.music.service.AlbumService;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public Page queryPageAlbum(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Album> datas = albumMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize = albumMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public int deleteBachAlbum(Data data) {
        return albumMapper.deleteBachAlbum(data);
    }

    @Override
    public int deleteAlbum(Integer id) {
        return albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int saveAlbum(Album album) {
        return albumMapper.insert(album);
    }
}
