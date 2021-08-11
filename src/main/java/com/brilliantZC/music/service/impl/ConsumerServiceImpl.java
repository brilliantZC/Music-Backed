package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.ConsumerDao;
import com.brilliantZC.music.entity.Consumer;
import com.brilliantZC.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前端用户service实现类
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerDao, Consumer> implements ConsumerService {
    @Autowired
    private ConsumerDao consumerDao;

    @Override
    public List<Consumer> allConsumer() {
        return consumerDao.selectList(null);
    }

    @Override
    public List<Consumer> consumerOfName(String username) {
        return consumerDao.selectList(new QueryWrapper<Consumer>().like("username",username));
    }
}
