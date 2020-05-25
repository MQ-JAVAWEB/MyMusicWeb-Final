package com.mq.music.controller;

import com.mq.music.bean.Song;
import com.mq.music.bean.Tuijian;
import com.mq.music.service.SongService;
import com.mq.music.service.TuiJianService;
import com.mq.music.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserCommentController {
    @Autowired
    private TuiJianService tuiJianService;

    @Autowired
    private SongService songService;


    @ResponseBody
    @RequestMapping("/doAddComment")
    public Object doAddContent(Tuijian tuijian) {
        AjaxResult result = new AjaxResult();
        try {
            String songname  = tuijian.getSongname();
            String singer = tuijian.getSinger();
            Song song = songService.getSong(songname,singer);
            if (song == null) {
                result.setSuccess(false);
                result.setMessage("音乐库中不存在！");
            }else {
                int count = tuiJianService.saveTuiJian(tuijian);

                result.setSuccess(count == 1);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("保存数据失败！");
        }
        return result;
    }
}
