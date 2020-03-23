package com.mq.music.controller;

import com.mq.music.service.UserService;
import com.mq.music.util.AjaxResult;
import com.mq.music.util.Page;
import com.mq.music.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserListController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toUserList")
    public String toUserList(){
        return "user/userList";
    }


    @ResponseBody
    @RequestMapping("/userList")
    public Object userList(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
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

            Page page = userService.queryPageUser(paramMap);

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
