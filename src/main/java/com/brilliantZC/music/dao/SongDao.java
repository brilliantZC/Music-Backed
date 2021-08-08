package com.brilliantZC.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brilliantZC.music.entity.Song;
import org.springframework.stereotype.Repository;

/**
 * 歌曲Dao
 */
@Repository
public interface SongDao extends BaseMapper<Song> {
}
