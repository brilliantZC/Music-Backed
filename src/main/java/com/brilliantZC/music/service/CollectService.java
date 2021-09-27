package com.brilliantZC.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brilliantZC.music.entity.Collect;

import java.util.List;

public interface CollectService extends IService<Collect> {
    //查找所有收藏
    public List<Collect> allCollect();
}
