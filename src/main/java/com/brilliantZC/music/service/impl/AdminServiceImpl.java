package com.brilliantZC.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brilliantZC.music.dao.AdminDao;
import com.brilliantZC.music.entity.Admin;
import com.brilliantZC.music.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * 管理员service实现类
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

}
