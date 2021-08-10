package com.brilliantZC.music.controller;

import com.brilliantZC.music.dao.SongListDao;
import com.brilliantZC.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songlist")
public class SongListController {
    @Autowired
    private SongListDao songListDao;
    @Autowired
    private SongListService songListService;
}
