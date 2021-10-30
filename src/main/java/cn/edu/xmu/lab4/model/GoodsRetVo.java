package cn.edu.xmu.lab4.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品视图对象
 * @author Ming Qiu
 **/
@Data
@ApiModel(description = "商品对象")
public class GoodsRetVo {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品序号")
    private String goodsSn;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品图片")
    private String imageUrl;

    @ApiModelProperty(value = "商品状态")
    private Integer state;

    private String gmtCreate;

    private String gmtModified;

    @ApiModelProperty(value = "禁用")
    private Boolean disabled;

    /**
     * 构造函数，由Goods对象创建Vo
     * @param goods goods对象
     */
    public GoodsRetVo(Goods goods) {
        this.id = goods.getId();
        this.goodsSn = goods.getGoodsSn();
        this.name = goods.getName();
        this.imageUrl = goods.getImageUrl();
        this.state = Goods.Status.UNPUBLISHED.getCode();
        this.gmtCreate = null;
        this.gmtModified = null;
        this.disabled = goods.getDisabled();
    }
}
