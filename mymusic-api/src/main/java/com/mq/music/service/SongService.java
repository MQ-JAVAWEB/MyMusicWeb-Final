package com.mq.music.service;

import com.mq.music.bean.Song;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;

import java.util.Map;

public interface SongService {
    Page queryPageSong(Map paramMap);

    int deleteSong(Integer id);

    int deleteBachMusic(Data data);

    int saveMusic(Song song);
}