package com.brilliantZC.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.brilliantZC.music.dao.SongDao;
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

    //查询所有歌曲
    @RequestMapping("/allSong")
    private Object allSong(){
        return songService.allSong();
    }
    //删除歌曲
    @RequestMapping("/delete")
    private R deleteSong(@RequestParam("id") Integer id){

        //先查询到数据库中对应的文件地址，删除掉他在进行下面的数据库记录删除
        Song song = songService.getById(id);
        String defaultPic = "/img/songPic/music.png";
        if(!song.getPic().equals(defaultPic)){
            String picpath = System.getProperty("user.dir")+song.getPic().replace("/",System.getProperty("file.separator"));
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

    /**
     * 更新歌曲图片
     */
    @RequestMapping("/updateSongPic")
    public R updateSongPic(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id") int id){

        Song songpic = songService.getById(id);
        String defaultPic = "/img/songPic/music.png";

        if(avatorFile.isEmpty()){
            return R.error().put("code",0).put("msg","文件上传失败！！！");
        }
        if(!songpic.getPic().equals(defaultPic)){
            String picpath = System.getProperty("user.dir")+songpic.getPic().replace("/",System.getProperty("file.separator"));
            File file = new File(picpath);
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
                +System.getProperty("file.separator")+"songPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songPic/"+fileName;
        try{
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            songService.updateById(song);
            return R.ok().put("code",1).put("msg","上传成功！！！").put("pic",storeAvatorPath);

        }catch (IOException e){
            e.printStackTrace();
            return R.error().put("code",0).put("msg","文件上传失败！！！"+e.getMessage());
        }
    }

    /**
     * 更新歌曲
     */
    @RequestMapping("/updateSongUrl")
    public R updateSongUrl(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id") int id){

        Song songurl = songService.getById(id);

        if(avatorFile.isEmpty()){
            return R.error().put("code",0).put("msg","文件上传失败！！！");
        }
        String filePathurl = System.getProperty("user.dir")+songurl.getUrl().replace("/",System.getProperty("file.separator"));

        File file2 = new File(filePathurl);
        // 判断目录或文件是否存在
        if (!file2.exists()) {  // 不存在返回 false
            return R.error().put("code",0).put("msg","删除文件不存在");
        } else {
            file2.delete();
        }
        //文件名 = 当前事件毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
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
        String storeAvatorPath = "/song/"+fileName;
        try{
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            String time = simpleDateFormat.format(date);
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            song.setUpdateTime(time);
            songService.updateById(song);
            return R.ok().put("code",1).put("msg","上传成功！！！").put("avator",storeAvatorPath);

        }catch (IOException e){
            e.printStackTrace();
            return R.error().put("code",0).put("msg","文件上传失败！！！"+e.getMessage());
        }
    }

    //根据歌曲id查询歌曲
    @RequestMapping("/detail")
    public Object songOfSongId(@RequestParam("songId") Integer songId){

        return songService.getById(songId);
    }

    //根据歌曲名精确查询歌曲
    @RequestMapping("/songOfSongName")
    public Object songOfSongName(@RequestParam("songName") String songName){

        return songService.songOfSongName(songName);
    }

    //根据歌曲名模糊查询歌曲
    @RequestMapping("/likeSongOfName")
    public Object likeSongOfName(@RequestParam("name") String name){

        return songService.songOfSongNameLike(name);
    }


}
