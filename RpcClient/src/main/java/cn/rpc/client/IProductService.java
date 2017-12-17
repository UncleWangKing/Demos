package cn.rpc.client;

import cn.rpc.client.bean.Product;

public interface IProductService {
    public Product queryById(Long id);
}
