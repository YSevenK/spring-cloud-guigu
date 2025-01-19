package com.ysevenk.order.service.impl;

import com.ysevenk.order.bean.Order;
import com.ysevenk.order.service.OrderService;
import com.ysevenk.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;


    @Override
    public Order createOrder(Long productId, Long userId) {
        // Product product = getProductFromRemote(productId);
        // Product product = getProductFromRemoteWithLoadBalance(productId);
        Product product = getProductFromRemoteWithLoadBalanceAnnotation(productId);
        Order order = new Order();
        order.setId(1L);
        // 总金额
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickname("zsan");
        order.setAddress("成都");
        // 远程调用 查询商品列表
        order.setProductList(Arrays.asList(product));
        return order;
    }

    private Product getProductFromRemote(Long productId){
        // 远程调用
        // 1.获取商品服务所在机器ip port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);

        // 远程URL地址
        String url = "http://" +instance.getHost()+":"+instance.getPort()+"/product/"+productId;

        log.info("远程请求: {}",url);
        // 2.给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    // 负载均衡
    private Product getProductFromRemoteWithLoadBalance(Long productId){
        // 远程调用
        // 1.获取商品服务所在机器ip port
        ServiceInstance choose = loadBalancerClient.choose("service-product");

        // 远程URL地址
        String url = "http://" +choose.getHost()+":"+choose.getPort()+"/product/"+productId;

        log.info("远程请求: {}",url);
        // 2.给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    // 负载均衡-基于注解
    private Product getProductFromRemoteWithLoadBalanceAnnotation(Long productId){
        // 远程调用
        // 1.获取商品服务所在机器ip port
        // ServiceInstance choose = loadBalancerClient.choose("service-product");

        // 远程URL地址
        // String url = "http://" +choose.getHost()+":"+choose.getPort()+"/product/"+productId;
        String url = "http://service-product/product/"+productId;
        log.info("远程请求: {}",url);
        // 2.给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
