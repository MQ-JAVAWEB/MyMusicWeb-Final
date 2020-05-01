package com.mq.music.controller;

import com.mq.music.bean.Song;
import com.mq.music.service.SongService;
import com.mq.music.util.*;
import com.mq.music.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SongListController {

    @Autowired
    private SongService songService;

    @RequestMapping("/toMusicList")
    public String toMusicList(){
        return "song/musicList";
    }

    @RequestMapping("/toAddMusic")
    public String toAddMusic(){
        return "song/addMusic";
    }

    public final static String UPLOAD_PATH_PREFIX="/static/song_js/playlist/";
    public final static String UPLOAD_PATH_PIC="/static/song_js/playlist/covers/";
    @RequestMapping("/doAddMusic")
    public Object doAddMusic(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("uploadFile1") MultipartFile uploadFile1, Song song, HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        song.setUrl(uploadFile.getOriginalFilename());
        song.setPic(uploadFile1.getOriginalFilename());
        try {
            if (uploadFile.isEmpty()){
                result.setMessage("请选择上传文件！");
                return result;
            }
            if (uploadFile1.isEmpty()){
                result.setMessage("请选择上传文件！");
                return result;
            }
            String realPath = new String("mymusic-main/src/main/resources/" + UPLOAD_PATH_PREFIX);
            String realPathPic = new String("mymusic-main/src/main/resources/" + UPLOAD_PATH_PIC);
            File file = new File(realPath);
            File file1 = new File(realPathPic);
            if (!file.isDirectory()){
                file.mkdirs();
            }
            if (!file1.isDirectory()){
                file1.mkdirs();
            }
//            String pictureUrl=singer.getPicture();
//            String[] url=pictureUrl.split("\\\\");
            String Name= changeEnglish.change(song.getUrl());
            String newUrlName= uuidSplit.createUUID(6)+Name;
            String PicName= changeEnglish.change(song.getPic());
            String newPicName= uuidSplit.createUUID(6)+PicName;

            File newFile = new File(file.getAbsolutePath() + File.separator + newUrlName);
            File newFile1 = new File(file1.getAbsolutePath() + File.separator + newPicName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            uploadFile1.transferTo(newFile1);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/song_js/playlist/" + newUrlName;
            String filePath1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/song_js/playlist/covers/" + newPicName;
            song.setUrl(newUrlName);
            song.setPic(newPicName);
            song.setSongtype(song.getSongtype());
            int count = songService.saveMusic(song);
            if (count!=1){
                return "/toAddMusic";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/toMusicList";
    }



    @RequestMapping("/doAddUserMusic")
    public Object doAddUserMusic(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("uploadFile1") MultipartFile uploadFile1,Song song, HttpServletRequest request) {
            AjaxResult result = new AjaxResult();
            song.setUrl(uploadFile.getOriginalFilename());
            song.setPic(uploadFile1.getOriginalFilename());

            try {
                if (uploadFile.isEmpty()){
                    result.setMessage("请选择上传文件！");
                    return result;
                }
                if (uploadFile1.isEmpty()){
                    result.setMessage("请选择上传文件！");
                    return result;
                }
                String realPath = new String("mymusic-main/src/main/resources/" + UPLOAD_PATH_PREFIX);
                String realPathPic = new String("mymusic-main/src/main/resources/" + UPLOAD_PATH_PIC);
                File file = new File(realPath);
                File file1 = new File(realPathPic);
                if (!file.isDirectory()){
                    file.mkdirs();
                }
                if (!file1.isDirectory()){
                    file1.mkdirs();
                }
//            String pictureUrl=singer.getPicture();
//            String[] url=pictureUrl.split("\\\\");
                String Name= changeEnglish.change(song.getUrl());
                String newUrlName= uuidSplit.createUUID(6)+Name;
                String PicName= changeEnglish.change(song.getPic());
                String newPicName= uuidSplit.createUUID(6)+PicName;

                File newFile = new File(file.getAbsolutePath() + File.separator + newUrlName);
                File newFile1 = new File(file1.getAbsolutePath() + File.separator + newPicName);
                //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
                uploadFile.transferTo(newFile);
                uploadFile1.transferTo(newFile1);
                String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/song_js/playlist/" + newUrlName;
                String filePath1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/song_js/playlist/covers" + newPicName;
                song.setUrl(newUrlName);
                song.setPic(newPicName);
                song.setSongtype(song.getSongtype());
                int count = songService.saveMusic(song);
            if (count!=1){
                return "/toAddUserMusic";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/member";
    }


    //专辑列表显示
    @ResponseBody
    @RequestMapping("/songList")
    public Object songList(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                            @RequestParam(value = "pagesize", required = false, defaultValue = "5") Integer pagesize,
                            String queryText) {
        AjaxResult result = new AjaxResult();
        try {

            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);
            //paramMap.put("queryText", queryText);

            if (StringUtil.isNotEmpty(queryText)) {
                paramMap.put("queryText", queryText);
            }

            Page page = songService.queryPageSong(paramMap);

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败");
        }
        return result;
    }

    //批量删除专辑
    @ResponseBody
    @RequestMapping("/doDeleteBatchMusic")
    public Object doDeleteBatchMusic(Data data) {
        AjaxResult result = new AjaxResult();
        try {
            int count=songService.deleteBachMusic(data);


            result.setSuccess(count==data.getDatasMusic().size());

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }




    //删除专辑
    @ResponseBody
    @RequestMapping("/doDeleteSong")
    public Object doDeleteSong(Integer id) {
        AjaxResult result = new AjaxResult();
        try {
            int count=songService.deleteSong(id);


            result.setSuccess(count==1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }

}
