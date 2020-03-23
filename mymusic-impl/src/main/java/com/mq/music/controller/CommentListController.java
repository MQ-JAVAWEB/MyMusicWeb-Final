package com.mq.music.controller;

import com.mq.music.service.CommentService;
import com.mq.music.util.AjaxResult;
import com.mq.music.util.Page;
import com.mq.music.util.StringUtil;
import com.mq.music.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentListController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/toCommentList")
    public String toCommentList(){
        return "comment/commentList";
    }



    @ResponseBody
    @RequestMapping("/commentList")
    public Object commentlist(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
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

            Page page = commentService.queryPageComment(paramMap);

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
    @RequestMapping("/doDeleteComment")
    public Object doDeleteComment(Integer id) {
        AjaxResult result = new AjaxResult();
        try {
            int count=commentService.deleteComment(id);


            result.setSuccess(count==1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/doDeleteCommentBatch")
    public Object  doDeleteCommentBatch(Data data) {
        AjaxResult result = new AjaxResult();
        try {
            int count=commentService.deleteBachComment(data);


            result.setSuccess(count==data.getDatasComment().size());

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }



}
