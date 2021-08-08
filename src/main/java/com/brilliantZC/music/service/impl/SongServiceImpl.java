package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.SongDao;
import com.brilliantZC.music.entity.Song;
import com.brilliantZC.music.service.SongService;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl extends ServiceImpl<SongDao, Song> implements SongService {
}
