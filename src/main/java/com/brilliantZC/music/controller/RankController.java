package com.brilliantZC.music.controller;

import com.brilliantZC.music.dao.RankDao;
import com.brilliantZC.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankDao rankDao;
    @Autowired
    private RankService rankService;


}
