package com.mq.music.vo;

import com.mq.music.bean.*;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Manager> managerList = new ArrayList<Manager>();
    private List<Manager> datas = new ArrayList<Manager>();

    private List<Singer>  singerList= new ArrayList<Singer>();
    private List<Singer> datasSinger = new ArrayList<Singer>();

    private List<Album>  albumList= new ArrayList<Album>();
    private List<Album> datasAlbum = new ArrayList<Album>();

    private List<Comment>  commentList= new ArrayList<Comment>();
    private List<Comment> datasComment = new ArrayList<Comment>();

    private List<Tuijian>  tuijianList= new ArrayList<Tuijian>();
    private List<Tuijian> datasTuiJian = new ArrayList<Tuijian>();

    public List<Tuijian> getTuijianList() {
        return tuijianList;
    }

    public void setTuijianList(List<Tuijian> tuijianList) {
        this.tuijianList = tuijianList;
    }

    public List<Tuijian> getDatasTuiJian() {
        return datasTuiJian;
    }

    public void setDatasTuiJian(List<Tuijian> datasTuiJian) {
        this.datasTuiJian = datasTuiJian;
    }

    private List<Song>  MusicList= new ArrayList<Song>();
    private List<Song> datasMusic = new ArrayList<Song>();

    public List<Song> getMusicList() {
        return MusicList;
    }

    public void setMusicList(List<Song> musicList) {
        MusicList = musicList;
    }

    public List<Song> getDatasMusic() {
        return datasMusic;
    }

    public void setDatasMusic(List<Song> datasMusic) {
        this.datasMusic = datasMusic;
    }

    public List<Comment> getDatasComment() {
        return datasComment;
    }

    public void setDatasComment(List<Comment> datasComment) {
        this.datasComment = datasComment;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public List<Album> getDatasAlbum() {
        return datasAlbum;
    }

    public void setDatasAlbum(List<Album> datasAlbum) {
        this.datasAlbum = datasAlbum;
    }

    public List<Singer> getSingerList() {
        return singerList;
    }

    public void setSingerList(List<Singer> singerList) {
        this.singerList = singerList;
    }

    public List<Singer> getDatasSinger() {
        return datasSinger;
    }

    public void setDatasSinger(List<Singer> datasSinger) {
        this.datasSinger = datasSinger;
    }

    public List<Manager> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Manager> managerList) {
        this.managerList = managerList;
    }

    public List<Manager> getDatas() {
        return datas;
    }

    public void setDatas(List<Manager> datas) {
        this.datas = datas;
    }

}
