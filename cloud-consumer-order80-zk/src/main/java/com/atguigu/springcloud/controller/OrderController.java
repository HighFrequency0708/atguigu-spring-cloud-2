package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 请填写类的描述
 *
 * @author GuofanLee
 * @date 2020-03-06 14:53
 */
@Slf4j
@RestController
public class OrderController {

    //通过服务名调用
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("consumer/payment/zk")
    public String create() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
    }

}