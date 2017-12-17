package cn.rpc.service;

import cn.rpc.client.IProductService;
import cn.rpc.client.bean.Product;

import java.math.BigDecimal;

public class ProductServiceImpl implements IProductService {
    public Product queryById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("water");
        product.setPrice(new BigDecimal(1.0));

        return product;
    }
}
