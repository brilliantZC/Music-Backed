package com.brilliantZC.music.controller;

import com.brilliantZC.music.dao.SingerDao;
import com.brilliantZC.music.entity.Singer;
import com.brilliantZC.music.service.SingerService;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    private R deleteSinger(@RequestParam("id") Integer id){

        //如果歌手头像不是默认的，删除文件
        Singer singer = singerService.getById(id);
        String defaultPic = "/img/singerPic/hhh.jpg";
        if(!singer.getPic().equals(defaultPic)){
            String filePath = System.getProperty("user.dir")+singer.getPic().replace("/",System.getProperty("file.separator"));
            File file = new File(filePath);
            // 判断目录或文件是否存在
            if (!file.exists()) {  // 不存在返回 false
                return R.error().put("code",0).put("msg","删除文件不存在");
            } else {
                file.delete();
            }
        }

        singerDao.deleteById(id);
        return R.ok().put("code",1).put("msg","删除成功！！！");
    }

    @RequestMapping("/selectPrimaryKey")
    private Object selectPrimaryKey(@RequestParam("id") Integer id){

        return singerService.selectPrimaryKey(id);
    }

    @RequestMapping("/allSinger")
    private Object allSinger(){

        return singerService.allSinger();
    }

    @RequestMapping("/singerOfSex")
    private Object singerOfSex(@RequestParam("sex") Integer sex){

        return singerService.singerOfSex(sex);
    }

    @RequestMapping("/singerOfName")
    private Object singerOfName(@RequestParam("name") String name){

        return singerService.singerOfName(name);
    }

    /**
     * 更新歌手图片
     */
    @RequestMapping("/updateSingerPic")
    public R updateSingerPic(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id") int id){

        Singer singerpic = singerService.getById(id);
        String defaultPic = "/img/singerPic/hhh.jpg";
        if(avatorFile.isEmpty()){
            return R.error().put("code",0).put("msg","文件上传失败！！！");
        }
        if(!singerpic.getPic().equals(defaultPic)){
            String filePath = System.getProperty("user.dir")+singerpic.getPic().replace("/",System.getProperty("file.separator"));
            File file = new File(filePath);
            // 判断目录或文件是否存在
            if (!file.exists()) {  // 不存在返回 false
                return R.error().put("code",0).put("msg","删除文件不存在");
            } else {
                file.delete();
            }
        }
        //文件名 = 当前事件毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/singerPic/"+fileName;
        try{
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            singerService.updateById(singer);
            return R.ok().put("code",1).put("msg","上传成功！！！").put("pic",storeAvatorPath);

        }catch (IOException e){
            e.printStackTrace();
            return R.error().put("code",0).put("msg","文件上传失败！！！"+e.getMessage());
        }
    }

}
