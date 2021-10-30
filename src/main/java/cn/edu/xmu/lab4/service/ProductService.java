package cn.edu.xmu.lab4.service;

import cn.edu.xmu.lab4.dao.ProductDao;
import cn.edu.xmu.lab4.model.*;
import cn.edu.xmu.lab4.util.ResponseCode;
import cn.edu.xmu.lab4.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductDao productDao;

    /**
     * 获取某个货品信息，仅展示相关内容
     * 使用Redis缓存
     * @param id 货品id
     * @return 货品对象
     */
    public ReturnObject<VoObject> findByIdWithRedis(Integer id) {
        ProductPo queryObj = new ProductPo();
        queryObj.setId(id);
        ReturnObject<VoObject> retProduct = null;
        ReturnObject<List<Product>> returnObject = productDao.findProductWithRedis(queryObj);
        //ReturnObject<List<Goods>> returnObject = goodsDao.findGoodsWithProduct(queryObj);
        if (returnObject.getCode().equals(ResponseCode.OK)) {
            if (returnObject.getData().size() == 1) {
                retProduct = new ReturnObject<>(returnObject.getData().get(0));
            }else{
                retProduct = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            }
        }else{
            retProduct = new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg());
        }
        return retProduct;
    }

    /**
     * 获取某个货品信息，仅展示相关内容
     * 不使用Redis缓存
     * @param id 货品id
     * @return 货品对象
     */
    public ReturnObject<VoObject> findByIdWithoutRedis(Integer id) {
        ProductPo queryObj = new ProductPo();
        queryObj.setId(id);
        ReturnObject<VoObject> retProduct = null;
        ReturnObject<List<Product>> returnObject = productDao.findProductWithoutRedis(queryObj);
        //ReturnObject<List<Goods>> returnObject = goodsDao.findGoodsWithProduct(queryObj);
        if (returnObject.getCode().equals(ResponseCode.OK)) {
            if (returnObject.getData().size() == 1) {
                retProduct = new ReturnObject<>(returnObject.getData().get(0));
            }else{
                retProduct = new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            }
        }else{
            retProduct = new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg());
        }
        return retProduct;
    }
}
