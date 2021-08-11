package com.brilliantZC.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brilliantZC.music.entity.ListSong;
import org.springframework.stereotype.Repository;

/**
 * 歌单里的歌曲Dao
 */
@Repository
public interface ListSongDao extends BaseMapper<ListSong> {
}
