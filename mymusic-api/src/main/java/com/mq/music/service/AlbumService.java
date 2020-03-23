package com.mq.music.service;

import com.mq.music.bean.Album;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;

import java.util.Map;

public interface AlbumService {
    Page queryPageAlbum(Map paramMap);



    int deleteBachAlbum(Data data);

    int deleteAlbum(Integer id);

    int saveAlbum(Album album);
}
