package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.SongDao;
import com.brilliantZC.music.entity.Song;
import com.brilliantZC.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl extends ServiceImpl<SongDao, Song> implements SongService {

    @Autowired
    private SongDao songDao;

    @Override
    public List<Song> songOfSingerId(Integer id) {
        List<Song> songs = songDao.selectList(new QueryWrapper<Song>().eq("singer_id",id));
        return songs;
    }

    @Override
    public List<Song> songOfName(String name) {
        List<Song> songs = songDao.selectList(new QueryWrapper<Song>().like("name",name));
        return songs;
    }
}
