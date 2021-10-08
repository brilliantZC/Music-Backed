package com.brilliantZC.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.brilliantZC.music.dao.CollectDao;
import com.brilliantZC.music.entity.Collect;
import com.brilliantZC.music.service.CollectService;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectDao collectDao;
    @Autowired
    private CollectService collectService;

    @RequestMapping("/add")
    private R addCollect(@RequestBody Collect collect) {
        if(collect.getSongId() == null || collect.getSongId().equals("")){
            return R.error().put("code",0).put("msg", "收藏歌曲为空！！！");
        }
        if(collectDao.selectList(new QueryWrapper<Collect>().eq("user_id",collect.getUserId()).eq("song_id",collect.getSongId())).size() != 0){
            return R.error().put("code",2).put("msg", "已收藏！！！");
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String time = simpleDateFormat.format(date);
        collect.setCreateTime(time);

        collectDao.insert(collect);
        return R.ok().put("code", 1).put("msg", "收藏成功！！！");
    }

    @RequestMapping("/delete")
    private R deleteCollect(@RequestParam("id") Integer id) {
        collectDao.deleteById(id);
        return R.ok().put("code", 1).put("msg", "删除成功！！！");
    }

    @RequestMapping("/deletebyusersong")
    private R deletebyusersong(@RequestParam("userId") Integer userId,@RequestParam("songId") Integer songId){
        Collect collect = collectDao.selectOne(new QueryWrapper<Collect>().eq("user_id", userId).eq("song_id", songId));
        collectDao.deleteById(collect.getId());
        return R.ok().put("code", 1).put("msg", "删除成功！！！");
    }

    @RequestMapping("/allCollect")
    private Object allCollect(){
        return collectService.allCollect();
    }

    @RequestMapping("/collectOfUserId")
    private Object collectOfUserId(@RequestParam("userId") Integer userId){
        return collectDao.selectList(new QueryWrapper<Collect>().eq("user_id",userId));

    }
}
