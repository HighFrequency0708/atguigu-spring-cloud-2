package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 请填写类的描述
 *
 * @author GuofanLee
 * @date 2020-03-06 00:18
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "payment/add")
    public CommonResult<Integer> add(@RequestBody Payment payment) {
        int result = paymentService.add(payment);
        log.info("插入结果：{}", result);
        if (result > 0) {
            return new CommonResult<>(200, "success", result);
        } else {
            return new CommonResult<>(-1, "error");
        }
    }

    @GetMapping(value = "payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：{}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "success", payment);
        } else {
            return new CommonResult<>(-1, "查询结果为空");
        }
    }

}
