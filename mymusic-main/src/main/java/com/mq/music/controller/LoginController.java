package com.mq.music.controller;

import com.mq.music.bean.Messager;
import com.mq.music.service.MessagerService;
import com.mq.music.util.AjaxResult;
import com.mq.music.util.Const;
import com.mq.music.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private MessagerService messagerService;

    @RequestMapping("/getAllManager")
    @ResponseBody
    public List<Messager> getAllManager(){
        List<Messager> manager= messagerService.getAllManager();
        return manager;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("/dologin")
    public Object doLogin(String username,String password,String type,
                          HttpSession session){

        AjaxResult result=new AjaxResult();

        try {
            Map<String,Object> paramMap=new HashMap<String,Object>();
            paramMap.put("username", username);
            paramMap.put("password", MD5Util.digest(password));
            paramMap.put("type", type);

            Messager messager=messagerService.queryMessagerLogin(paramMap);

            session.setAttribute(Const.LOGIN_MESSAGER, messager);

            result.setSuccess(true);
        } catch (Exception e) {
            result.setMessage("登录失败！");
            e.printStackTrace();
            result.setSuccess(false);
        }
        //防止重复提交表单
        return result;
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
