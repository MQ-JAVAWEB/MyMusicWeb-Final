package com.mq.music.mapper;

import com.mq.music.bean.Comment;
import com.mq.music.bean.CommentExample;
import com.mq.music.vo.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@RequestParam("record") Comment record, @RequestParam("example") CommentExample example);

    int updateByExample(@RequestParam("record") Comment record, @RequestParam("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);



    List<Comment> queryList(Map paramMap);

    Integer queryCount(Map paramMap);

    int deleteBachComment(Data data);
}