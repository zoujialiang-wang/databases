package com.example.demo.controller;


import com.example.demo.entity.WmsWareInfo;
import com.example.demo.service.IWmsWareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 仓库信息 前端控制器
 * </p>
 *
 * @author ZouJiaLiang
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/demo/wms-ware-info")
public class WmsWareInfoController {
    @Autowired
    private IWmsWareInfoService iWmsWareInfoService;

    @PostMapping("/select")
    public List<WmsWareInfo> select(){
        return iWmsWareInfoService.test();
    }
}
