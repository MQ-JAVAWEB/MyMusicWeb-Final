package com.mq.music.controller;

import com.mq.music.bean.Comment;
import com.mq.music.service.CommentService;
import com.mq.music.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserContentController {
    @Autowired
    private CommentService commentService;


    @ResponseBody
    @RequestMapping("/doAddContent")
    public Object doAddContent(Comment comment) {
        AjaxResult result = new AjaxResult();
        try {

            int count = commentService.saveComment(comment);

            result.setSuccess(count == 1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("保存数据失败");
        }
        return result;
    }

}
