package com.example.demo.service;

import com.example.demo.entity.WmsWareInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 仓库信息 服务类
 * </p>
 *
 * @author ZouJiaLiang
 * @since 2020-11-05
 */
public interface IWmsWareInfoService extends IService<WmsWareInfo> {

    List<WmsWareInfo> test();
}
