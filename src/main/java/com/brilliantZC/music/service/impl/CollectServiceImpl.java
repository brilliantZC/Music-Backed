package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.CollectDao;
import com.brilliantZC.music.entity.Collect;
import com.brilliantZC.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectDao, Collect> implements CollectService {

    @Autowired
    private CollectDao collectDao;

    @Override
    public List<Collect> allCollect() {
        List<Collect> collects = collectDao.selectList(new QueryWrapper<Collect>());
        return collects;
    }
}
