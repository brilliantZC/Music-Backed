package com.brilliantZC.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brilliantZC.music.entity.Song;

import java.util.List;

public interface SongService extends IService<Song> {
    //根据歌手id找到歌曲
    public List<Song> songOfSingerId(Integer id);
    //歌曲名模糊查询
    public List<Song> songOfName(String name);
    //歌曲名精确查询
    public List<Song> songOfSongName(String name);
    //歌曲名模糊查询
    public List<Song> songOfSongNameLike(String name);
    //所有歌曲
    public List<Song> allSong();

}
