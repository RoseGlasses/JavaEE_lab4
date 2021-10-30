package cn.edu.xmu.lab4.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品规格
 * @author Ming Qiu
 **/
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements VoObject, Serializable {

    /**
     * 代理对象
     */
    private ProductPo productPo;

    private Goods goods;

    /**
     * 无参构造函数
     */
    public Product() {
        this.productPo = new ProductPo();
    }

    /**
     * 构造函数
     * @param productPo 代理对象
     */
    public Product(ProductPo productPo) {
        this.productPo = productPo;
    }

    public Integer getId() {
        return productPo.getId();
    }

    public String getProductSn() {
        return productPo.getProductSn();
    }

    public String getName() {
        return productPo.getName();
    }

    public Integer getOriginalPrice() {
        return productPo.getOriginalPrice();
    }

    public Integer getWeight() {
        return productPo.getWeight();
    }

    public String getImageUrl() { return productPo.getImageUrl(); }

    public Goods.Status getState() {
        return Goods.Status.getStatusByCode(productPo.getState());
    }

    public String getDetail() { return productPo.getDetail(); }

    public Boolean getDisabled() { return productPo.getDisabled(); }

    public Goods getGoods() { return goods; }

    public void setId(Integer id) {
        productPo.setId(id);
    }

    public void setProductSn(String productSn) {
        productPo.setProductSn(productSn);
    }

    public void setName(String desc) {
        productPo.setName(desc);
    }

    public void setOriginalPrice(Integer counterPrice) {
        productPo.setOriginalPrice(counterPrice);
    }

    public void setWeight(Integer weight) {
        productPo.setWeight(weight);
    }

    public void setImageUrl(String imageUrl) { productPo.setImageUrl(imageUrl); }

    public void setState(Goods.Status state) {
        productPo.setState(state.getCode());
    }

    public void setDetail(String Detail) { productPo.setDetail(getDetail()); }

    public void setDisabled(Boolean disabled) { productPo.setDisabled(disabled); }

    public void setGoods(Goods goods) { this.goods = goods; }

    @Override
    public boolean equals(Object o) {
        return productPo.equals(o);
    }

    @Override
    public int hashCode() {
        return productPo.hashCode();
    }

    @Override
    public String toString() {
        return productPo.toString();
    }

    @Override
    public Object createVo() {
        return new ProductRetVo(this);
    }
}
