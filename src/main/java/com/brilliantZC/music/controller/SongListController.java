package com.brilliantZC.music.controller;

import com.brilliantZC.music.dao.SongListDao;
import com.brilliantZC.music.entity.Singer;
import com.brilliantZC.music.entity.SongList;
import com.brilliantZC.music.service.SongListService;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/songlist")
public class SongListController {
    @Autowired
    private SongListDao songListDao;
    @Autowired
    private SongListService songListService;

    //添加歌单
    @RequestMapping("/add")
    private R addSongList(@RequestBody SongList songList){

        songListDao.insert(songList);
        return R.ok().put("code",1).put("msg","添加成功！！！");
    }

    //修歌单
    @RequestMapping("/update")
    private R updateSongList(@RequestBody SongList songList){

        songListDao.updateById(songList);
        return R.ok().put("code",1).put("msg","更新成功！！！");
    }

    //删除歌单
    @RequestMapping("/delete")
    private R deleteSongList(@RequestParam("id") Integer id){

        //如果歌单头像不是默认的，删除文件
        SongList songList = songListService.getById(id);
        String defaultPic = "/img/songListPic/defaultsonglist.png";
        if(!songList.getPic().equals(defaultPic)){
            String filePath = System.getProperty("user.dir")+songList.getPic().replace("/",System.getProperty("file.separator"));
            File file = new File(filePath);
            // 判断目录或文件是否存在
            if (!file.exists()) {  // 不存在返回 false
                return R.error().put("code",0).put("msg","删除文件不存在");
            } else {
                file.delete();
            }
        }

        songListDao.deleteById(id);
        return R.ok().put("code",1).put("msg","删除成功！！！");
    }

    // 获取所有歌单
    @RequestMapping("/allSongList")
    private Object allSongList(){

        return songListService.allSongList();
    }

    //根据标题查询
    @RequestMapping("/songListOfTitle")
    private Object songListOfTitle(@RequestParam("title") String title){

        return songListService.songListOfTitle(title);
    }


    /**
     * 更新歌单图片
     */
    @RequestMapping("/updateSongListPic")
    public R updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id){

        SongList songListPic = songListService.getById(id);
        String defaultPic = "/img/songListPic/defaultsonglist.png";
        if(avatorFile.isEmpty()){
            return R.error().put("code",0).put("msg","文件上传失败！！！");
        }
        if(!songListPic.getPic().equals(defaultPic)){
            String filePath = System.getProperty("user.dir")+songListPic.getPic().replace("/",System.getProperty("file.separator"));
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
                +System.getProperty("file.separator")+"songListPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songListPic/"+fileName;
        try{
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            songListService.updateById(songList);
            return R.ok().put("code",1).put("msg","上传成功！！！").put("pic",storeAvatorPath);

        }catch (IOException e){
            e.printStackTrace();
            return R.error().put("code",0).put("msg","文件上传失败！！！"+e.getMessage());
        }
    }
}
