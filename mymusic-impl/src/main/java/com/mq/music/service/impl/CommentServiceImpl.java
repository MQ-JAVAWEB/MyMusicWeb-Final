package com.mq.music.service.impl;

import com.mq.music.bean.Comment;
import com.mq.music.mapper.CommentMapper;
import com.mq.music.service.CommentService;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper  commentMapper;

    @Override
    public Page queryPageComment(Map paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);

        List<Comment> datas = commentMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize = commentMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public int deleteComment(Integer id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBachComment(Data data) {
        return commentMapper.deleteBachComment(data);
    }
}
