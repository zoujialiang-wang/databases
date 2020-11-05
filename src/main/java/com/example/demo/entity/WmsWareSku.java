package com.example.demo.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品库存
 * </p>
 *
 * @author jobob
 * @since 2020-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="WmsWareSku对象", description="商品库存")
public class WmsWareSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "sku_id")
    private Long skuId;

    @ApiModelProperty(value = "仓库id")
    private Long wareId;

    @ApiModelProperty(value = "库存数")
    private Integer stock;

    @ApiModelProperty(value = "sku_name")
    private String skuName;

    @ApiModelProperty(value = "锁定库存")
    private Integer stockLocked;


}
