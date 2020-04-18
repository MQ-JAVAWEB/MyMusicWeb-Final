package com.mq.music.controller;

import com.mq.music.bean.User;
import com.mq.music.service.UserService;
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
public class UserMessageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/toUpdateMessage")
    public  String toUpdateMessage(Integer id,Map map){
        User user=userService.getUserById(id);
        map.put("user",user );
        return "index/Updategerenxinxi";
    }

    @ResponseBody
    @RequestMapping("/MessageList")
    public Object MessageList(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
                           @RequestParam(value = "pagesize", required = false, defaultValue = "1") Integer pagesize
                           ) {
        AjaxResult result = new AjaxResult();
        try {

            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);
            //paramMap.put("queryText", queryText);

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

    @ResponseBody
    @RequestMapping("/doUpdateMessage")
    public Object doUpdateMessage(User user){
        AjaxResult result=new AjaxResult();
        try {



            int count=userService.updateUser(user);

            result.setSuccess(count==1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("修改用户失败");
        }


        return result;
    }

}
