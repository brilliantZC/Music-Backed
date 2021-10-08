package com.brilliantZC.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.brilliantZC.music.dao.CommentDao;
import com.brilliantZC.music.dao.ConsumerDao;
import com.brilliantZC.music.entity.Comment;
import com.brilliantZC.music.entity.Singer;
import com.brilliantZC.music.service.CommentService;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/add")
    private R addComment(@RequestBody Comment comment) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String time = simpleDateFormat.format(date);
        comment.setCreateTime(time);

        comment.setUp(0);
        System.out.println(comment.toString());
        commentDao.insert(comment);
        return R.ok().put("code", 1).put("msg", "评论成功！！！");
    }

    @RequestMapping("/update")
    private R updateComment(@RequestBody Comment comment) {

        commentDao.updateById(comment);
        return R.ok().put("code", 1).put("msg", "更新成功！！！");
    }

    @RequestMapping("/delete")
    private R deleteComment(@RequestParam("id") Integer id) {
        commentDao.deleteById(id);
        return R.ok().put("code", 1).put("msg", "删除成功！！！");
    }

    @RequestMapping("/allComment")
    private Object allComment(){
        return commentService.allComment();
    }

    //查询某个歌曲下的所有评论
    @RequestMapping("/commentOfSongId")
    private Object commentOfSongId(@RequestParam("songId") Integer songId){
        return commentDao.selectList(new QueryWrapper<Comment>().eq("song_id",songId));
    }

    //查询某个歌单下的所有评论
    @RequestMapping("/commentOfSongListId")
    private Object commentOfSongListId(@RequestParam("songListId") Integer songListId){
        return commentDao.selectList(new QueryWrapper<Comment>().eq("song_list_id",songListId));
    }

    //点赞
    @RequestMapping("/like")
    private Object like(@RequestParam("id") Integer id){
        Comment comment = commentDao.selectById(id);
        comment.setUp(comment.getUp()+1);
        commentDao.updateById(comment);
        return R.ok().put("code", 1).put("msg", "点赞成功！！！");
    }
}
