package com.brilliantZC.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brilliantZC.music.entity.Consumer;

import java.util.List;

/**
 * 前端用户service接口
 */
public interface ConsumerService extends IService<Consumer> {
    //查询前端所有用户
    public List<Consumer> allConsumer();
    //根据名字模糊查询用户
    public List<Consumer> consumerOfName(String username);
}
