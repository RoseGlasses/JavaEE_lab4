package cn.edu.xmu.lab4.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ming Qiu
 **/
@Data
@Alias("GoodsPo")
public class GoodsPo {

    private Integer id;

    private String name;

    private Integer brandId;

    private Integer categoryId;

    private Integer freightId;

    private Integer shopId;

    private String goodsSn;

    private String detail;

    private String imageUrl;

    private Boolean disabled;

    private List<ProductPo> productList;
}
