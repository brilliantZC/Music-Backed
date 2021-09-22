package com.brilliantZC.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/*
评分
 */
@TableName("rank")
public class Rank implements Serializable {
    /*主键*/
    @TableId
    private Integer id;
    /*歌单id*/
    private Integer songListId;
    /*用户id*/
    private Integer consumerId;
    /*评分*/
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", songListId=" + songListId +
                ", consumerId=" + consumerId +
                ", score=" + score +
                '}';
    }
}
