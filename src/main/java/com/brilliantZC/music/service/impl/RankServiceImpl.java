package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.RankDao;
import com.brilliantZC.music.entity.Rank;
import com.brilliantZC.music.service.RankService;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl extends ServiceImpl<RankDao, Rank> implements RankService {
}
