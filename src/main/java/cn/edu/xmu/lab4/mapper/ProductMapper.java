package cn.edu.xmu.lab4.mapper;

import cn.edu.xmu.lab4.model.GoodsPo;
import cn.edu.xmu.lab4.model.ProductPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 用ProductPo对象找，
     * @param productPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
     * @return ProductPo 对象列表
     */
    List<ProductPo> findProduct(ProductPo productPo);

    int createProduct(ProductPo productPo);
}
