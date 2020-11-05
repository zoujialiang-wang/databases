package com.example.demo.controller;


import com.example.demo.entity.WmsPurchaseDetail;
import com.example.demo.service.IWmsPurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/demo/wms-purchase-detail")
public class WmsPurchaseDetailController {

    @Autowired
    private IWmsPurchaseDetailService iWmsPurchaseDetailService;

    @PostMapping("select")
    public List<WmsPurchaseDetail> select(){
        List<WmsPurchaseDetail> list = iWmsPurchaseDetailService.list();
        return list;
    }
}
