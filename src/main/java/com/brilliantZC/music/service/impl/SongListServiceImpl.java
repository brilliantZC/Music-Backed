package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.SongListDao;
import com.brilliantZC.music.entity.SongList;
import com.brilliantZC.music.service.SongListService;
import org.springframework.stereotype.Service;

@Service
public class SongListServiceImpl extends ServiceImpl<SongListDao, SongList> implements SongListService {
}
