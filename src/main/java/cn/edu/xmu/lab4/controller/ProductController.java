package cn.edu.xmu.lab4.controller;

import cn.edu.xmu.lab4.model.ProductRetVo;
import cn.edu.xmu.lab4.model.VoObject;
import cn.edu.xmu.lab4.service.ProductService;
import cn.edu.xmu.lab4.util.ResponseCode;
import cn.edu.xmu.lab4.util.ResponseUtil;
import cn.edu.xmu.lab4.util.ReturnObject;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(value = "货品API", tags = "货品API")
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/products", produces = "application/json;charset=UTF-8")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @ApiOperation(value = "获得一个货品对象",  produces="application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "id", value ="货品对象id" ,required = true)
    })
    @ApiResponses({
    })
    @GetMapping("/withredis/{id}")
    public Object getProductByIdWithRedis(@PathVariable("id") Integer id) {
        ReturnObject<VoObject> returnObject =  productService.findByIdWithRedis(id);
        ResponseCode code = returnObject.getCode();
        switch (code){
            case RESOURCE_ID_NOTEXIST:
                httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
                return ResponseUtil.fail(returnObject.getCode(), returnObject.getErrmsg());
            case OK:
                ProductRetVo productRetVo = (ProductRetVo) returnObject.getData().createVo();
                return ResponseUtil.ok(productRetVo);
            default:
                return ResponseUtil.fail(code);
        }
    }

    @ApiOperation(value = "获得一个货品对象",  produces="application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "id", value ="货品对象id" ,required = true)
    })
    @ApiResponses({
    })
    @GetMapping("/withoutredis/{id}")
    public Object getProductByIdWithoutRedis(@PathVariable("id") Integer id) {
        ReturnObject<VoObject> returnObject =  productService.findByIdWithoutRedis(id);
        ResponseCode code = returnObject.getCode();
        switch (code){
            case RESOURCE_ID_NOTEXIST:
                httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
                return ResponseUtil.fail(returnObject.getCode(), returnObject.getErrmsg());
            case OK:
                ProductRetVo productRetVo = (ProductRetVo) returnObject.getData().createVo();
                return ResponseUtil.ok(productRetVo);
            default:
                return ResponseUtil.fail(code);
        }
    }

}
