package cn.edu.xmu.lab4.model;

import cn.edu.xmu.lab4.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 商品规格视图对象
 * @author Ming Qiu
 **/
@Data
@ApiModel(description = "商品规格视图对象")
public class ProductRetVo {

    @ApiModelProperty(value = "规格id")
    private Integer id;

    @ApiModelProperty(value = "描述")
    private String name;

    @ApiModelProperty(value = "规格序号")
    private String productSn;

    @ApiModelProperty(value = "规格描述")
    private String detail;

    @ApiModelProperty(value = "图片")
    private String imageUrl;

    @ApiModelProperty(value = "零售价")
    private Integer originalPrice;

    private Integer price;

    private Integer inventory;

    @ApiModelProperty(value = "规格状态")
    private Integer state;

    @ApiModelProperty(value = "重量(克)")
    private Integer weight;

    private String gmtCreate;

    private String gmtModified;

    @ApiModelProperty(value = "商品")
    private GoodsRetVo goodsRetVo;

    @ApiModelProperty(value = "禁用")
    private Boolean disabled;

    private Boolean shareable;

    /**
     * 由Vo对象创建Product对象
     *
     * @return Product对象
     */
    public ProductRetVo(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.productSn = product.getProductSn();
        this.detail = product.getDetail();
        this.imageUrl = product.getImageUrl();
        this.originalPrice = product.getOriginalPrice();
        this.price = null;
        this.inventory = null;
        this.state = product.getState().getCode();
        this.weight = product.getWeight();
        this.gmtCreate = null;
        this.gmtModified = null;
        this.goodsRetVo = new GoodsRetVo(product.getGoods());
        this.disabled = product.getDisabled();
        this.shareable = false;
    }
}
