package com.mq.music.controller;

import com.mq.music.bean.Singer;
import com.mq.music.bean.Song;
import com.mq.music.service.SingerService;
import com.mq.music.service.SongService;
import com.mq.music.service.TuiJianService;
import com.mq.music.util.AjaxResult;
import com.mq.music.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/toRegSinger")
    public String toRegSinger(){
        return "index/regSinger";
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
    public String toBofangqi( Integer id,Map map,@RequestParam("singer") String singerName){
        Song song=songService.getSongById(id);
        Singer singer=singerService.getSingerPictureByName(singerName);
        map.put("song",song);
        map.put("singer",singer);
        return "index/bofang";
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

    @ResponseBody
    @RequestMapping("/mainTuiJianMusic")
    public Object mainTuiJianMusic(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                                    @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize
    ) {
        AjaxResult result = new AjaxResult();
        try {

            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);
            //paramMap.put("queryText", queryText);

            Page page = tuiJianService.queryPageTuiJianMusic(paramMap);

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
