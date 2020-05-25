package com.mq.music.controller;

import com.mq.music.bean.Singer;
import com.mq.music.bean.Song;
import com.mq.music.service.AlbumService;
import com.mq.music.service.SingerService;
import com.mq.music.service.SongService;
import com.mq.music.util.AjaxResult;
import com.mq.music.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SingerShowController {

    @Autowired
    private SingerService singerService;

    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/toSingerShow")
    public String toSingerShow(Integer id, HttpSession session) {
        Singer singer=singerService.querySingerById(id);
        session.setAttribute("singer",singer);
        return "index/singerShow";
    }



    @ResponseBody
    @RequestMapping("/SingerMusic")
    public Object SingerMusic(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                               @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize
    ,String singer) {
        AjaxResult result = new AjaxResult();
        try {

            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);
            paramMap.put("singer",singer);
            Page page = songService.queryPageAllMusic(paramMap);

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
    @RequestMapping("/SingerAlbum")
    public Object SingerAlbum(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                               @RequestParam(value = "pagesize", required = false, defaultValue = "5") Integer pagesize
    ,String singer) {
        AjaxResult result = new AjaxResult();
        try {
            pagesize=5;
            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);
            paramMap.put("singer",singer);
            Page page = albumService.queryPageAllAlbum(paramMap);

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

