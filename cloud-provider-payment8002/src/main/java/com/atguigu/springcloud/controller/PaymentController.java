package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.api.PaymentApi;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 请填写类的描述
 *
 * @author GuofanLee
 * @date 2020-03-06 00:18
 */
@Slf4j
@RestController
public class PaymentController implements PaymentApi {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Override
    public CommonResult<Integer> add(Payment payment) {
        int result = paymentService.add(payment);
        log.info("插入结果：{}", result);
        if (result > 0) {
            return new CommonResult<>(200, "插入成功，Server-Port：" + serverPort, result);
        } else {
            return new CommonResult<>(-1, "插入失败，Server-Port：" + serverPort);
        }
    }

    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：{}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功，Server-Port：" + serverPort, payment);
        } else {
            return new CommonResult<>(-1, "查询结果为空，Server-Port：" + serverPort);
        }
    }

    @Override
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("services：{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info("ServiceId：{}\tHost：{}\tPort：{}\tUri：{}", instance.getServiceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }
        return discoveryClient;
    }

    @Override
    public String paymentFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
