package com.mq.music.controller;

import com.mq.music.bean.Album;
import com.mq.music.service.AlbumService;
import com.mq.music.util.AjaxResult;
import com.mq.music.util.Page;
import com.mq.music.util.StringUtil;
import com.mq.music.util.changeEnglish;
import com.mq.music.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AlbumListController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/toAlbumList")
    public  String toAlbumList(){
        return "album/albumList";
    }

     @RequestMapping("/toAddAlbum")
    public String toAddAlbum(){
        return "album/addAlbum";
    }




    @RequestMapping("/toUpdateAlbum")
    public String toUpdateAlbum(Integer id, HttpSession session){

        Album album=albumService.getAlbumById(id);
        session.setAttribute("album",album );


        return "album/updateAlbum";
    }


    @ResponseBody
    @RequestMapping("/doUpdateAlbum")
    public Object doUpdateAlbum(Album album){
        AjaxResult result=new AjaxResult();
        try {



            int count=albumService.updateAlbum(album);

            result.setSuccess(count==1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("修改失败");
        }


        return result;
    }

    public final static String UPLOAD_PATH_PREFIX="/static/AlbumPicture/";
    @RequestMapping("/doAddAlbum")
    public Object doAddAlbum(@RequestParam("uploadFile") MultipartFile uploadFile, Album album, HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        album.setPicture(uploadFile.getOriginalFilename());
        try {
            if (uploadFile.isEmpty()){
                result.setMessage("请选择上传文件！");
                return result;
            }
            String realPath = new String("mymusic-main/src/main/resources/" + UPLOAD_PATH_PREFIX);
            File file = new File(realPath);
            if (!file.isDirectory()){
                file.mkdirs();
            }
//            String pictureUrl=singer.getPicture();
//            String[] url=pictureUrl.split("\\\\");
            String pictureName= changeEnglish.change(album.getPicture());

            File newFile = new File(file.getAbsolutePath() + File.separator + pictureName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/AlbumPicture/" + pictureName;
            album.setPicture(pictureName);

            int count = albumService.saveAlbum(album);
            if (count!=1){
                return "/toAddAlbum";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/toAlbumList";
    }


    //专辑列表显示
    @ResponseBody
    @RequestMapping("/albumList")
    public Object albumList(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
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

            Page page = albumService.queryPageAlbum(paramMap);

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败");
        }
        return result;
    }

    //批量删除专辑
    @ResponseBody
    @RequestMapping("/doDeleteBatchAlbum")
    public Object doDeleteBatchAlbum(Data data) {
        AjaxResult result = new AjaxResult();
        try {
            int count=albumService.deleteBachAlbum(data);


            result.setSuccess(count==data.getDatasAlbum().size());

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }




    //删除专辑
    @ResponseBody
    @RequestMapping("/doDeleteAlbum")
    public Object doDeleteAlbum(Integer id) {
        AjaxResult result = new AjaxResult();
        try {
            int count=albumService.deleteAlbum(id);


            result.setSuccess(count==1);

        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败");
        }
        return result;
    }


}
