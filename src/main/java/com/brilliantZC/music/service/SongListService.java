package com.brilliantZC.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brilliantZC.music.entity.SongList;

import java.util.List;

public interface SongListService extends IService<SongList> {
    //获取所有歌单信息
    public List<SongList> allSongList();
    //根名字查询
    public List<SongList> songListOfTitle(String title);
}
