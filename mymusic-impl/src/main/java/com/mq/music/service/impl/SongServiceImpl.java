package com.mq.music.service.impl;

import com.mq.music.bean.Song;
import com.mq.music.mapper.SongMapper;
import com.mq.music.service.SongService;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public Page queryPageSong(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Song> datas = songMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize = songMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public int deleteSong(Integer id) {
        return songMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBachMusic(Data data) {
        return songMapper.deleteBachMusic(data);
    }

    @Override
    public int saveMusic(Song song) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String createtime = sdf.format(date);
        song.setCreatetime(createtime);
        song.setClicknum(0);
        return songMapper.insert(song);
    }

    @Override
    public Page queryPage(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Song> datas = songMapper.queryHotMusicList(paramMap);

        page.setDatas(datas);

        Integer totalsize = songMapper.queryHotMusicCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public Page queryPageNewMusic(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Song> datas = songMapper.queryNewMusicList(paramMap);

        page.setDatas(datas);

        Integer totalsize = songMapper.queryNewMusicCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public Page queryPageOriginalMusic(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Song> datas = songMapper.queryOriginalMusicList(paramMap);

        page.setDatas(datas);

        Integer totalsize = songMapper.queryOriginalMusicCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public Song getSongById(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateClick(Song song) {

        return songMapper.updateByPrimaryKey(song);
    }

    @Override
    public Song findById(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page queryPageAllMusic(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);
        page.setSinger((String) paramMap.get("singer"));
        String singer = page.getSinger();
        paramMap.put("singer",singer);
        List<Song> datas = songMapper.queryAllMusicList(paramMap);

        page.setDatas(datas);

        Integer totalsize = songMapper.queryCountAllMusic(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public Song getSong(String name, String singer) {
        return songMapper.selectByPrimaryName(name,singer);
    }

    @Override
    public Song getMusicById(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateMusic(Song song) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String createtime = sdf.format(date);
        song.setCreatetime(createtime);
        return songMapper.updateByPrimaryKey(song);
    }

}
