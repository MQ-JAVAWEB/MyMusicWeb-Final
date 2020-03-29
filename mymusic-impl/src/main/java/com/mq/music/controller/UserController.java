package com.mq.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/toMusic")
    public String toMusic(){
        return "index/music";
    }

    @RequestMapping("/toSinger")
    public String toSinger(){
        return "index/singer";
    }

    @RequestMapping("/toTuiJian")
    public String toTuiJian(){
        return "index/tuijian";
    }

    @RequestMapping("/toBoFang")
    public String toBoFang(){
        return "index/bofang";
    }
}
