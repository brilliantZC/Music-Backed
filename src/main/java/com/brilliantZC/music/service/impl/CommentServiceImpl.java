package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.CommentDao;
import com.brilliantZC.music.entity.Comment;
import com.brilliantZC.music.entity.Singer;
import com.brilliantZC.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public List<Comment> allComment() {
        List<Comment> comments = commentDao.selectList(new QueryWrapper<Comment>());
        return comments;
    }
}
