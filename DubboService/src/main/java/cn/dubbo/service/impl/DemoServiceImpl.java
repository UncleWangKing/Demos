package cn.dubbo.service.impl;

import cn.dubbo.service.DemoService;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}