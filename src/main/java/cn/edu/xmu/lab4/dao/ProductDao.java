package cn.edu.xmu.lab4.dao;

import cn.edu.xmu.lab4.mapper.GoodsMapper;
import cn.edu.xmu.lab4.mapper.ProductMapper;
import cn.edu.xmu.lab4.model.Goods;
import cn.edu.xmu.lab4.model.GoodsPo;
import cn.edu.xmu.lab4.model.Product;
import cn.edu.xmu.lab4.model.ProductPo;
import cn.edu.xmu.lab4.util.RedisUtil;
import cn.edu.xmu.lab4.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

    private Logger logger = LoggerFactory.getLogger(ProductDao.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${lab4.product.expiretime}")
    private long productTimeout;

    /**
     * 用ProductPo对象找Product对象,使用Redis缓存
     * @param productPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
     * @return  Product对象列表
     */
    public ReturnObject<List<Product>> findProductWithRedis(ProductPo productPo) {
        logger.info("findProduct: productPo =" + productPo);

        List<Product> retProduct = null;
        String key = null;
        if (null != productPo.getId()){
            key = "g_"+productPo.getId();
            Product product = (Product) redisUtil.get(key);
            if (null != product){
                logger.info("findProduct: hit redis cache, key = "+key);
                retProduct = new ArrayList<>(1);
                retProduct.add(product);
                return new ReturnObject<>(retProduct);
            }
        }

        List<ProductPo> productPos = productMapper.findProduct(productPo);
        logger.info("findProduct: productPos =" + productPos);
        retProduct = new ArrayList<>(productPos.size());
        GoodsPo goodsPo = new GoodsPo();

        for (ProductPo productItem : productPos) {
            Product item = new Product(productItem);
                goodsPo.setId(productItem.getGoodsId());
                List<GoodsPo> goodsPos = goodsMapper.findGoods(goodsPo);
                Goods goods = new Goods((goodsPos.get(0)));
                item.setGoods(goods);

            retProduct.add(item);
        }

        if (null != productPo.getId()){
            logger.info("findProduct: put into redis cache, key = "+key);
            if (retProduct.size() != 0) {
                redisUtil.set(key, retProduct.get(0), productTimeout);
            }else{
                redisUtil.set(key, null, productTimeout);
            }
        }

        logger.info("findProduct: retProduct = "+ retProduct);
        return new ReturnObject<>(retProduct);
    }

    /**
     * 用ProductPo对象找Product对象,不使用Redis缓存
     * @param productPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
     * @return  Product对象列表
     */
    public ReturnObject<List<Product>> findProductWithoutRedis(ProductPo productPo) {
        logger.info("findProduct: productPo =" + productPo);

        List<Product> retProduct = null;
        List<ProductPo> productPos = productMapper.findProduct(productPo);
        logger.info("findProduct: productPos =" + productPos);
        retProduct = new ArrayList<>(productPos.size());
        GoodsPo goodsPo = new GoodsPo();

        for (ProductPo productItem : productPos) {
            Product item = new Product(productItem);
            goodsPo.setId(productItem.getGoodsId());
            List<GoodsPo> goodsPos = goodsMapper.findGoods(goodsPo);
            Goods goods = new Goods((goodsPos.get(0)));
            item.setGoods(goods);

            retProduct.add(item);
        }

        logger.info("findProduct: retProduct = "+ retProduct);
        return new ReturnObject<>(retProduct);
    }
}
