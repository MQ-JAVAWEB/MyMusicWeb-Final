package com.mq.music.controller;

import com.mq.music.bean.Singer;
import com.mq.music.service.SingerService;
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
public class SingerListController {



    @Autowired
    private SingerService singerService;

    @RequestMapping("/toSingerList")
    public String toSingerList() {
        return "singer/singerList";
    }

    @RequestMapping("/toAddSinger")
    public String toAddSinger() {
        return "singer/addSinger";
    }








    public final static String UPLOAD_PATH_PREFIX="/static/song_js/playlist/covers/";
    @RequestMapping("/doAddSinger")
    public Object doAddSinger(@RequestParam("uploadFile") MultipartFile uploadFile, Singer singer, HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        singer.setPicture(uploadFile.getOriginalFilename());
        try {
            if (uploadFile.isEmpty()){
                result.setMessage("请选择上传文件！");
                return result;
            }
            String realPath = new String("mymusic-main/src/main/resources/" + UPLOAD_PATH_PREFIX);
            File file = new File(realPath);
            if (!file.isDirectory()){
                file.mkdirs();
            }
//            String pictureUrl=singer.getPicture();
//            String[] url=pictureUrl.split("\\\\");
            String Name= changeEnglish.change(singer.getPicture());
            String newUrlName= uuidSplit.createUUID(6)+Name;

            File newFile = new File(file.getAbsolutePath() + File.separator + newUrlName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/song_js/playlist/covers/" + newUrlName;
            singer.setPicture(newUrlName);

            int count = singerService.saveSinger(singer);
            if (count!=1){
                return "singer/addSinger";
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "redirect:/toSingerList";
    }


    //批量删除歌手
    @ResponseBody
    @RequestMapping("/doDeleteBatchSinger")
    public Object doDeleteBatchSinger(Data data) {
        AjaxResult result = new AjaxResult();
        try {
            int count=singerService.deleteBachSinger(data);


            result.setSuccess(count==data.getDatasSinger().size());

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }




    //删除歌手
    @ResponseBody
    @RequestMapping("/doDeleteSinger")
    public Object doDeleteSinger(Integer id) {
        AjaxResult result = new AjaxResult();
        try {
            int count=singerService.deleteSinger(id);


            result.setSuccess(count==1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }





    //显示歌手信息列表
    @ResponseBody
    @RequestMapping("/singerList")
    public Object singerList(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                             @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize,
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

            Page page = singerService.queryPage(paramMap);

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败");
        }
        return result;
    }
}
