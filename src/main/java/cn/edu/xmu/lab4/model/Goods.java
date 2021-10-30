package cn.edu.xmu.lab4.model;

import io.swagger.models.auth.In;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Goods implements VoObject, Serializable {
    /**
     * 商品状态
     */
    public enum Status {
        UNPUBLISHED(0,"未发布"),
        PUBLISHED(1,"发布"),
        DELETED(2,"废弃");

        private static final Map<Integer, Status> stateMap;

        static { //由类加载机制，静态块初始加载对应的枚举属性到map中，而不用每次取属性时，遍历一次所有枚举值
            stateMap = new HashMap();
            for (Status enum1 : values()) {
                stateMap.put(enum1.code, enum1);
            }
        }

        private int code;
        private String description;

        Status(int code, String description) {
            this.code=code;
            this.description=description;
        }

        public static Status getStatusByCode(Integer code){
            return stateMap.get(code);
        }

        public Integer getCode(){
            return code;
        }

        public String getDescription() {return description;}

    }
    /**
     * 代理对象
     */
    private GoodsPo goodsPo;

    private List<Product> productList;

    /**
     * 构造函数
     */
    public Goods() {
        this.goodsPo = new GoodsPo();
    }

    /**
     * 构造函数
     */
    public Goods(GoodsPo goodsPo) {
        this.goodsPo = goodsPo;
    }

    /**
     * 由Goods对象创建Vo对象
     */
    @Override
    public Object createVo(){
        return new GoodsRetVo(this);
    }

    /**
     * 获得内部的代理对象
     * @return GoodsPo对象
     */
    public GoodsPo gotGoodsPo(){
        return this.goodsPo;
    }

    public Integer getId() {
        return goodsPo.getId();
    }

    public String getGoodsSn() {
        return goodsPo.getGoodsSn();
    }

    public String getName() {
        return goodsPo.getName();
    }

    public Integer getCategoryId() {
        return goodsPo.getCategoryId();
    }

    public void setCategoryId(Integer categoryId) {
        goodsPo.setCategoryId(categoryId);
    }

    public void setBrandId(Integer brandId) {
        goodsPo.setBrandId(brandId);
    }

    public void setId(Integer id) {
        goodsPo.setId(id);
    }

    public Integer getBrandId() {
        return goodsPo.getBrandId();
    }

    public void setName(String name) {
        goodsPo.setName(name);
    }

    public void setGoodsSn(String goodsSn) {
        goodsPo.setGoodsSn(goodsSn);
    }

    public Integer getFreightId() { return goodsPo.getFreightId(); }

    public void setFreightId(Integer freightId) {goodsPo.setFreightId(freightId);}

    public String getDetail() {return goodsPo.getDetail();}

    public void setDetail(String detail) {goodsPo.setDetail(detail);}

    public Boolean getDisabled() {return goodsPo.getDisabled();}

    public void setDisabled(Boolean disabled) {goodsPo.setDisabled(disabled);}

    public String getImageUrl() {return goodsPo.getImageUrl();}

    public void setImageUrl(String imageUrl) {goodsPo.setImageUrl(imageUrl);}

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        return goodsPo.hashCode();
    }

    @Override
    public String toString() {
        return goodsPo.toString();
    }

    @Override
    public boolean equals(Object o) {
        return goodsPo.equals(((Goods)o).goodsPo);
    }

}