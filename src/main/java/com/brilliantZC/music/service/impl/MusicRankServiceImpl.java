package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.MusicRankDao;
import com.brilliantZC.music.entity.MusicRank;
import com.brilliantZC.music.service.MusicRankService;
import org.springframework.stereotype.Service;

@Service
public class MusicRankServiceImpl extends ServiceImpl<MusicRankDao, MusicRank> implements MusicRankService {
}
