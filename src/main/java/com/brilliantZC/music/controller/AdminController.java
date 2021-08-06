package com.brilliantZC.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.brilliantZC.music.entity.Admin;
import com.brilliantZC.music.service.AdminService;
import com.brilliantZC.music.utils.Consts;
import com.brilliantZC.music.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;
    /**
     * 判断是否登录成功
     */
    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public R loginStatus(@RequestBody Admin admin){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", admin.getName()).eq("password",admin.getPassword());
        Admin admin1 = adminService.getOne(queryWrapper);
        if(admin1 != null){
            return R.ok().put("code",1).put("msg","登陆成功！！！");
        }else {
            return R.error().put("code",0).put("msg","用户名或密码错误！！！");
        }
    }
}






















