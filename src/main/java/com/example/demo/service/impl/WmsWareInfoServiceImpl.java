package com.example.demo.service.impl;

import com.example.demo.entity.WmsWareInfo;
import com.example.demo.mapper.WmsWareInfoMapper;
import com.example.demo.service.IWmsWareInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 仓库信息 服务实现类
 * </p>
 *
 * @author ZouJiaLiang
 * @since 2020-11-05
 */
@Service
public class WmsWareInfoServiceImpl extends ServiceImpl<WmsWareInfoMapper, WmsWareInfo> implements IWmsWareInfoService {

    @Autowired
    private WmsWareInfoMapper wmsWareInfoMapper;

    @Override
    public List<WmsWareInfo> test() {
        return wmsWareInfoMapper.test();

    }
}
