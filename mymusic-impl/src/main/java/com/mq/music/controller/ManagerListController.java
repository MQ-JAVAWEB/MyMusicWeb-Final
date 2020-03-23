package com.mq.music.controller;

import com.mq.music.bean.Manager;
import com.mq.music.service.ManagerService;
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

public class ManagerListController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/toManagerList")
    public String toUserList() {
        return "manager/managerList";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "manager/add";
    }

    //批量删除管理员
    @ResponseBody
    @RequestMapping("/doDeleteBatch")
    public Object doDeleteBatch(Data data) {
        AjaxResult result = new AjaxResult();
        try {
            int count = managerService.deleteManagerBatch(data);

            result.setSuccess(count == data.getDatas().size());

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }

    //删除管理员
    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Integer id) {
        AjaxResult result = new AjaxResult();
        try {
            int count = managerService.deleteManager(id);

            result.setSuccess(count == 1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }

    //添加管理员
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Manager manager) {
        AjaxResult result = new AjaxResult();
        try {

            int count = managerService.savaManager(manager);

            result.setSuccess(count == 1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("保存数据失败");
        }
        return result;
    }

    //显示管理员信息
    @ResponseBody
    @RequestMapping("/managerList")
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

            Page page = managerService.queryPage(paramMap);

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
