package com.mq.music.service;

import com.mq.music.bean.Comment;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;

import java.util.Map;

public interface CommentService {
    Page queryPageComment(Map paramMap);

    int deleteComment(Integer id);

    int deleteBachComment(Data data);

    int saveComment(Comment comment);
}
