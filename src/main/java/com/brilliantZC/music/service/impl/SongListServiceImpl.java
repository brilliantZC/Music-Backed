package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.SongListDao;
import com.brilliantZC.music.entity.SongList;
import com.brilliantZC.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl extends ServiceImpl<SongListDao, SongList> implements SongListService {
    @Autowired
    private SongListDao songListDao;

    @Override
    public List<SongList> allSongList() {
        return songListDao.selectList(null);
    }

    @Override
    public List<SongList> songListOfTitle(String title) {
        List<SongList> songLists = songListDao.selectList(new QueryWrapper<SongList>().like("title",title));
        return songLists;
    }
}
