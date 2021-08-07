package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.SingerDao;
import com.brilliantZC.music.entity.Singer;
import com.brilliantZC.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌手service实现类
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerDao, Singer> implements SingerService {

    @Autowired
    private SingerDao singerDao;

    @Override
    public Singer selectPrimaryKey(Integer id) {
        Singer singer = singerDao.selectById(id);

        return singer;
    }

    @Override
    public List<Singer> allSinger() {
        List<Singer> singers = singerDao.selectList(new QueryWrapper<Singer>().eq("sex",1).eq("sex",0));
        return singers;
    }

    @Override
    public List<Singer> singerOfName(String name) {
        List<Singer> singers = singerDao.selectList(new QueryWrapper<Singer>().like("name",name));
        return singers;
    }

    @Override
    public List<Singer> singerOfSex(Integer sex) {
        List<Singer> singers = singerDao.selectList(new QueryWrapper<Singer>().eq("sex",sex));
        return singers;
    }
}
