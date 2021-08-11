package com.brilliantZC.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 歌单里的歌曲entity
 */
@TableName("list_song")
public class ListSong implements Serializable {
    //主键
    @TableId
    private Integer id;
    //歌曲id
    private Integer songId;
    //歌单id
    private Integer songListId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    @Override
    public String toString() {
        return "ListSong{" +
                "id=" + id +
                ", songId=" + songId +
                ", songListId=" + songListId +
                '}';
    }
}
