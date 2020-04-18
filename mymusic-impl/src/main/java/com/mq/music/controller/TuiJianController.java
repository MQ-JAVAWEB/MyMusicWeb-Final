package com.mq.music.controller;

import com.mq.music.service.TuiJianService;
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
public class TuiJianController {

    @Autowired
    private TuiJianService tuiJianService;

    @RequestMapping("/toTuiJianList")
    public  String toTuiJianList(){
        return  "tuijian/tuijianList";
    }


    //批量删除推荐
    @ResponseBody
    @RequestMapping("/doDeleteBatchTuiJian")
    public Object doDeleteBatchTuiJian(Data data) {
        AjaxResult result = new AjaxResult();
        try {
            int count = tuiJianService.deleteTuiJianBatch(data);

            result.setSuccess(count == data.getDatasTuiJian().size());

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }

    //删除管理员
    @ResponseBody
    @RequestMapping("/doDeleteTuiJian")
    public Object doDeleteTuiJian(Integer id) {
        AjaxResult result = new AjaxResult();
        try {
            int count = tuiJianService.deleteTuiJian(id);

            result.setSuccess(count == 1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }

    //显示用户推荐信息
    @ResponseBody
    @RequestMapping("/TuiJianList")
    public Object TuiJianList(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
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

            Page page = tuiJianService.queryPage(paramMap);

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
