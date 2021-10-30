package cn.edu.xmu.lab4.mapper;

import cn.edu.xmu.lab4.model.GoodsPo;
import cn.edu.xmu.lab4.model.ProductPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
     /**
      * 用GoodsPo对象找，
      * @param goodsPo 条件对象，所有条件为AND，仅有索引的值可以作为条件
      * @return  GoodsPo对象列表
      */
     List<GoodsPo> findGoods(GoodsPo goodsPo);

     /**
      * 创建GoodsPo对象
      * @param goodsPo goodsPo对象
      * @return 1 成功
      */
     int createGoods(GoodsPo goodsPo);
}
