package com.brilliantZC.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.brilliantZC.music.dao.ListSongDao;
import com.brilliantZC.music.entity.ListSong;
import com.brilliantZC.music.service.ListSongService;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listsong")
public class ListSongController {
    @Autowired
    private ListSongDao listSongDao;
    @Autowired
    private ListSongService listSongService;

    //添加
    @RequestMapping("/add")
    private R addListSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        listSongDao.insert(listSong);
        return R.ok().put("code",1).put("msg","添加成功！！！");
    }


    //根据歌曲id和歌单歌曲id删除歌单里的歌曲
    @RequestMapping("/delete")
    private R deleteListSong(@RequestParam("songId") Integer songId,@RequestParam("songListId") Integer songListId){

        ListSong listSong = listSongService.getOne(new QueryWrapper<ListSong>().eq("song_id",songId).eq("song_list_id",songListId));
        listSongDao.deleteById(listSong.getId());
        return R.ok().put("code",1).put("msg","添加成功！！！");
    }


    //根据歌单id查询歌曲
    @RequestMapping("/detail")
    private Object detail(@RequestParam("id") Integer id){
        return listSongDao.selectList(new QueryWrapper<ListSong>().like("song_list_id",id));
    }



}
