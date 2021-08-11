package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.ListSongDao;
import com.brilliantZC.music.entity.ListSong;
import com.brilliantZC.music.service.ListSongService;
import org.springframework.stereotype.Service;

/**
 * 歌单里的歌曲service实现类
 */
@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongDao, ListSong> implements ListSongService {
}
