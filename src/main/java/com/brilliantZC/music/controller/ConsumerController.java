package com.brilliantZC.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.brilliantZC.music.dao.ConsumerDao;
import com.brilliantZC.music.entity.Consumer;
import com.brilliantZC.music.entity.Singer;
import com.brilliantZC.music.service.ConsumerService;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerDao consumerDao;
    @Autowired
    private ConsumerService consumerService;

    //添加前端用户
    @RequestMapping("/add")
    private R addConsumer(@RequestBody Consumer consumer){

        //判断当前用户名是否存在，若存在则拒绝添加
        Consumer consumer1 = consumerService.getOne(new QueryWrapper<Consumer>().eq("username",consumer.getUsername()));
        if(consumer1 != null ){
            return R.error().put("code",0).put("msg","用户名已存在！！！");
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String time = simpleDateFormat.format(date);
        consumer.setCreateTime(time);
        consumer.setUpdateTime(time);
        consumerDao.insert(consumer);
        return R.ok().put("code",1).put("msg","添加成功！！！");
    }

    //修改前端用户
    @RequestMapping("/update")
    private R updateConsumer(@RequestBody Consumer consumer){

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String time = simpleDateFormat.format(date);
        consumer.setUpdateTime(time);
        consumerDao.updateById(consumer);
        return R.ok().put("code",1).put("msg","更新成功！！！");
    }

    //删除前端用户
    @RequestMapping("/delete")
    private R deleteConsumer(@RequestParam("id") Integer id){

        //如果歌手头像不是默认的，删除文件
        Consumer consumer = consumerService.getById(id);
        String defaultPic = "/avatorImages/profile.png";
        if(!consumer.getAvator().equals(defaultPic)){
            String filePath = System.getProperty("user.dir")+consumer.getAvator().replace("/",System.getProperty("file.separator"));
            File file = new File(filePath);
            // 判断目录或文件是否存在
            if (!file.exists()) {  // 不存在返回 false
                return R.error().put("code",0).put("msg","删除文件不存在");
            } else {
                file.delete();
            }
        }

        consumerDao.deleteById(id);
        return R.ok().put("code",1).put("msg","删除成功！！！");
    }

    //查询所有前端用户
    @RequestMapping("/allConsumer")
    private Object allConsumer(){

        return consumerService.allConsumer();
    }

    //根据用户名模糊查询用户
    @RequestMapping("/consumerOfName")
    private Object singerOfName(@RequestParam("username") String username){

        return consumerService.consumerOfName(username);
    }


    /**
     * 更新前端图片
     */
    @RequestMapping("/updateConsumerPic")
    public R updateConsumerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id){

        Consumer consumerpic = consumerService.getById(id);
        String defaultPic = "/avatorImages/profile.png";
        if(avatorFile.isEmpty()){
            return R.error().put("code",0).put("msg","文件上传失败！！！");
        }
        if(!consumerpic.getAvator().equals(defaultPic)){
            String filePath = System.getProperty("user.dir")+consumerpic.getAvator().replace("/",System.getProperty("file.separator"));
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
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"avatorImages";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/avatorImages/"+fileName;
        try{
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            consumerService.updateById(consumer);
            return R.ok().put("code",1).put("msg","上传成功！！！").put("pic",storeAvatorPath);

        }catch (IOException e){
            e.printStackTrace();
            return R.error().put("code",0).put("msg","文件上传失败！！！"+e.getMessage());
        }
    }

    //前端用户登录
    @RequestMapping("/login")
    private R loginConsumer(@RequestBody Consumer consumer){
        Consumer consumer1 = consumerService.getOne(new QueryWrapper<Consumer>().eq("username",consumer.getUsername()).eq("password",consumer.getPassword()));
        if(consumer1 != null){
            return R.ok().put("code",1).put("userMsg",consumer1.getUsername()).put("msg","登录成功！！！");
        }
        return R.error().put("code",0).put("msg","登录失败！！！");
    }


}
