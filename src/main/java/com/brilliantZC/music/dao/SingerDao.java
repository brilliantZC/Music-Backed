package com.brilliantZC.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brilliantZC.music.entity.Singer;
import org.springframework.stereotype.Repository;

/**
 * 歌手Dao
 */
@Repository
public interface SingerDao extends BaseMapper<Singer> {
}
