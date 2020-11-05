package com.example.demo.mapper;

import com.example.demo.entity.WmsWareInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 仓库信息 Mapper 接口
 * </p>
 *
 * @author ZouJiaLiang
 * @since 2020-11-05
 */
public interface WmsWareInfoMapper extends BaseMapper<WmsWareInfo> {

    List<WmsWareInfo> test();
}
