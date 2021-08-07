package com.brilliantZC.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brilliantZC.music.entity.Singer;

import java.util.List;

public interface SingerService extends IService<Singer> {
    //根据主键查找整个对象
    public Singer selectPrimaryKey(Integer id);
    //查找所有歌手
    public List<Singer> allSinger();
    //根名字查询
    public List<Singer> singerOfName(String name);
    //根据性别查询
    public List<Singer> singerOfSex(Integer sex);
}
