package com.mq.music.controller;

import com.mq.music.bean.Manager;
import com.mq.music.bean.User;
import com.mq.music.service.ManagerService;
import com.mq.music.service.UserService;
import com.mq.music.util.AjaxResult;
import com.mq.music.util.Const;
import com.mq.music.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @ResponseBody
    @RequestMapping("/dologin")
    public Object doLogin(String username, String password, String type,
                          HttpSession session) {

        AjaxResult result = new AjaxResult();

        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("username", username);
            paramMap.put("password", MD5Util.digest(password));
            paramMap.put("type", type);

            if("user".equals(type)){
                Manager manager = managerService.queryManagerLogin(paramMap);
                session.setAttribute(Const.LOGIN_MANAGER, manager);
            }
            else {
                User user=userService.queryUserLogin(paramMap);
                session.setAttribute(Const.LOGIN_USER,user);

            }
            result.setData(type);
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
    public String main() {
        return "backstage/main";
    }

    @RequestMapping("/member")
    public String member(){
        return "index/main";
    }

    // @RequestMapping("/toManagerList")
//    public String toManagerList(){
//        return "manager/managerList";
//    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
       session.invalidate();
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping("/doreg")
    public Object doreg(User user, @RequestParam("repassword")String repassword) {
        AjaxResult result = new AjaxResult();
        try {
            if (!(user.getPassword().equals(repassword))  ) {
                if ("".equals(user.getPassword()) && "".equals(repassword)){
                    result.setSuccess(false);
                    result.setMessage("请输入密码");
                    return result;
                }
                result.setSuccess(false);
                result.setMessage("两次密码不一致");
                return result;
            }
            if (!("男".equals(user.getSex())) && !("女".equals(user.getSex())) && user.getSex()==""){
                result.setSuccess(false);
                result.setMessage("请输入正确性别");
                return result;
            }
            String mailRegex,mailName,mailDomain;
            mailName="^[0-9a-z]+\\w*";       //^表明一行以什么开头；^[0-9a-z]表明要以数字或小写字母开头；\\w*表明匹配任意个大写小写字母或数字或下划线
            mailDomain="([0-9a-z]+\\.)+[0-9a-z]+$";       //***.***.***格式的域名，其中*为小写字母或数字;第一个括号代表有至少一个***.匹配单元，而[0-9a-z]$表明以小写字母或数字结尾
            mailRegex=mailName+"@"+mailDomain;
            if (!(user.getEmail().matches(mailRegex))){
                if ("".equals(user.getEmail())){
                    result.setSuccess(false);
                    result.setMessage("请输入邮箱");
                    return result;
                }
                result.setSuccess(false);
                result.setMessage("请输入正确的邮箱地址");
                return result;
            }

            int count = userService.saveUser(user);

            result.setSuccess(count == 1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("保存数据失败");
        }
        return result;
    }

}
