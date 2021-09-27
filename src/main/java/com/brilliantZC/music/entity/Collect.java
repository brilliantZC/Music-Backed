package com.brilliantZC.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("collect")
public class Collect implements Serializable {
    /*主键*/
    @TableId
    private Integer id;
    /*用户id*/
    private Integer userId;
    /*收藏类型0歌曲1歌单*/
    private Byte type;
    /*歌曲id*/
    private Integer songId;
    /*歌单id*/
    private Integer songListId;
    /*创建时间*/
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", songId=" + songId +
                ", songListId=" + songListId +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
