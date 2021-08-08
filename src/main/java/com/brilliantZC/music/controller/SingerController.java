package com.brilliantZC.music.controller;

import com.brilliantZC.music.dao.SingerDao;
import com.brilliantZC.music.entity.Singer;
import com.brilliantZC.music.service.SingerService;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;
    @Autowired
    private SingerDao singerDao;

    @RequestMapping("/add")
    private R addSinger(@RequestBody Singer singer){

        singerDao.insert(singer);
        return R.ok().put("code",1).put("msg","添加成功！！！");
    }

    @RequestMapping("/update")
    private R updateSinger(@RequestBody Singer singer){

        singerDao.updateById(singer);
        return R.ok().put("code",1).put("msg","更新成功！！！");
    }

    @RequestMapping("/delete")
    private R deleteSinger(@PathVariable("id") Integer id){

        singerDao.deleteById(id);
        return R.ok().put("code",1).put("msg","删除成功！！！");
    }

    @RequestMapping("/selectPrimaryKey")
    private Object selectPrimaryKey(@PathVariable("id") Integer id){

        return singerService.selectPrimaryKey(id);
    }

    @RequestMapping("/allSinger")
    private Object allSinger(){

        return singerService.allSinger();
    }

    @RequestMapping("/singerOfSex")
    private Object singerOfSex(@PathVariable("sex") Integer sex){

        return singerService.singerOfSex(sex);
    }

    @RequestMapping("/singerOfName")
    private Object singerOfName(@PathVariable("name") String name){

        return singerService.singerOfName(name);
    }

}
