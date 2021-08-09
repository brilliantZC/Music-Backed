package com.brilliantZC.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.brilliantZC.music.dao.SongDao;
import com.brilliantZC.music.entity.Singer;
import com.brilliantZC.music.entity.Song;
import com.brilliantZC.music.service.SongService;
import com.brilliantZC.music.utils.Consts;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongDao songDao;
    @Autowired
    private SongService songService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        //获取前端传来的参数
        String singerId = request.getParameter("singerId").trim();  //所属歌手id
        String name = request.getParameter("name").trim();          //歌名
        String introduction = request.getParameter("introduction").trim();          //简介
        String pic = "/img/songPic/music.png";                     //默认图片
        String lyric = request.getParameter("lyric").trim();     //歌词
        //上传歌曲文件
        if(mpFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌曲上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/song/"+fileName;
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            String time = simpleDateFormat.format(date);

            mpFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            song.setCreateTime(time);
            song.setUpdateTime(time);
            songDao.insert(song);

            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            jsonObject.put("avator",storeUrlPath);
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"保存失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    //根据歌手id查询歌手
    @RequestMapping("/singer/detail")
    public Object songOfSingerId(@RequestParam("singerId") Integer singerId){

        return songService.songOfSingerId(singerId);
    }

    //模糊查询
    @RequestMapping("/songOfName")
    public Object songOfName(@RequestParam("name") String name){
        return songService.songOfName(name);
    }

    //更新歌曲信息
    @RequestMapping("/update")
    private R updateSong(@RequestBody Song song){

        songDao.updateById(song);
        return R.ok().put("code",1).put("msg","更新成功！！！");
    }

    //删除歌曲
    @RequestMapping("/delete")
    private R deleteSong(@RequestParam("id") Integer id){

        //先查询到数据库中对应的文件地址，删除掉他在进行下面的数据库记录删除
        Song song = songService.getById(id);
        String defaultPic = "/img/songPic/music.png";
        if(!song.getPic().equals(defaultPic)){
            String picpath = System.getProperty("user.dir")+song.getPic().replace("/",System.getProperty("file.separator"));
            System.out.println(picpath);
            File file = new File(picpath);
            // 判断目录或文件是否存在
            if (!file.exists()) {  // 不存在返回 false
                return R.error().put("code",0).put("msg","删除文件不存在");
            } else {
                file.delete();
            }
        }
        String filePath = System.getProperty("user.dir")+song.getUrl().replace("/",System.getProperty("file.separator"));

        File file1 = new File(filePath);
        // 判断目录或文件是否存在
        if (!file1.exists()) {  // 不存在返回 false
            return R.error().put("code",0).put("msg","删除文件不存在");
        } else {
            file1.delete();
        }
        songDao.deleteById(id);
        return R.ok().put("code",1).put("msg","删除成功！！！");
    }


}
