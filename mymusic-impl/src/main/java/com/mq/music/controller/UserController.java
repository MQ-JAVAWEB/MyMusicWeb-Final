package com.mq.music.controller;

import com.mq.music.bean.Song;
import com.mq.music.service.SingerService;
import com.mq.music.service.SongService;
import com.mq.music.service.TuiJianService;
import com.mq.music.util.AjaxResult;
import com.mq.music.util.Page;
import com.mq.music.util.StringUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private SongService songService;

    @Autowired
    private SingerService singerService;

    @Autowired
    private TuiJianService tuiJianService;


    @RequestMapping("/toMusic")
    public String toMusic(){
        return "index/music";
    }

    @RequestMapping("/toSinger")
    public String toSinger(){
        return "index/singer";
    }

    @RequestMapping("/toGeRenXinXi")
    public String toGeRenXinXi(){
        return "index/gerenxinxi";
    }

    @RequestMapping("/toAddUserMusic")
    public String toRegSinger(){
        return "index/addMusic";
    }



    @RequestMapping("/toTuiJian")
    public String toTuiJian(){
        return "index/tuijian";
    }

    @RequestMapping("/toBoFang")
    public String toBoFang(){
        return "index/bofang";
    }



    @RequestMapping("/toBofangqi")
    public String toBofangqi( Integer id,Map map,@RequestParam("songname")String name,String singer){
        Song songid = songService.getSong(name,singer);
//        if (songid.getId()==id) {
//            Song song=songService.getSongById(id);
//
//            int count =song.getClicknum();
//            count ++;
//            song.setClicknum(count);
//            songService.updateClick(song);
//
//            map.put("song",song);
//        }else  {
            int count =songid.getClicknum();
            count ++;
            songid.setClicknum(count);
            songService.updateClick(songid);

            map.put("song",songid);
//        }


        return "index/bofang";
    }



    @RequestMapping("/downLoadMusic")
    private void downLoadMusic (Integer id,HttpServletResponse response) throws IOException {
        Song song = songService.findById(id);
        String realPath  = ResourceUtils.getURL("classpath:").getPath()+"/static/song_js/playlist/"+ song.getUrl();
        String songName = song.getName()+"-"+song.getSinger()+".mp3";
        FileInputStream is=new FileInputStream(new File(realPath));
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(songName,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);

    }

    @ResponseBody
    @RequestMapping("/queryAllSinger")
    public Object queryAllSinger(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                             @RequestParam(value = "pagesize", required = false, defaultValue = "20") Integer pagesize,
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

    @ResponseBody
    @RequestMapping("/queryAllMusic")
    public Object queryAllMusic(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                               @RequestParam(value = "pagesize", required = false, defaultValue = "20") Integer pagesize
    ,String queryText) {
        AjaxResult result = new AjaxResult();
        try {

            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);
            paramMap.put("queryText", queryText);

            Page page = songService.queryPageSong(paramMap);

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/mainHotMusic")
    public Object mainHotMusic(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                              @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize
                              ) {
        AjaxResult result = new AjaxResult();
        try {

            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);
            //paramMap.put("queryText", queryText);

            Page page = songService.queryPage(paramMap);

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询失败");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/mainNewMusic")
    public Object mainNewMusic(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                               @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize
    ) {
        AjaxResult result = new AjaxResult();
        try {

            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);
            //paramMap.put("queryText", queryText);

            Page page = songService.queryPageNewMusic(paramMap);

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询失败");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/mainOriginalMusic")
    public Object mainOriginalMusic(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                               @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize
    ) {
        AjaxResult result = new AjaxResult();
        try {

            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);
            //paramMap.put("queryText", queryText);

            Page page = songService.queryPageOriginalMusic(paramMap);

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询失败");
        }
        return result;
    }


}
