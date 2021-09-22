package com.brilliantZC.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.brilliantZC.music.dao.MusicRankDao;
import com.brilliantZC.music.entity.MusicRank;
import com.brilliantZC.music.service.MusicRankService;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rank")
public class MusicRankController {
    @Autowired
    private MusicRankDao musicRankDao;
    @Autowired
    private MusicRankService musicRankService;

    /**
     * 新增评价
     * @param rank
     * @return
     */
    @RequestMapping("/add")
    private R addRank(@RequestBody MusicRank musicRank){

        musicRankDao.insert(musicRank);
        return R.ok().put("code",1).put("msg","评价成功！！！");
    }

    @RequestMapping("/sum")
    public int rankOfSongList(@RequestParam("id") Integer id){

        List<MusicRank> list = musicRankDao.selectList(new QueryWrapper<MusicRank>().eq("song_list_id",id));
        int rankNum = list.size();
        if(rankNum == 0){
            return 5;
        }
        //取总分
        int sum = 0;
        for (MusicRank r: list){
            sum += r.getScore();
        }
        sum = sum/rankNum;
        return sum;
    }


}
