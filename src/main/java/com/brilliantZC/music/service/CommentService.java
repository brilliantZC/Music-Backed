package com.brilliantZC.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brilliantZC.music.entity.Comment;
import com.brilliantZC.music.entity.Singer;

import java.util.List;

public interface CommentService extends IService<Comment> {
    //查找所有评论
    public List<Comment> allComment();
}
