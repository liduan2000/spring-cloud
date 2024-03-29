package com.duan.cloud.controller;

import com.duan.cloud.apis.PayFeignApi;
import com.duan.cloud.entities.PayDTO;
import com.duan.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        ResultData resultData = payFeignApi.getPayInfo(id);
        return resultData;
    }

    @DeleteMapping(value = "/pay/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        ResultData resultData = payFeignApi.deletePay(id);
        return resultData;
    }

    @PutMapping(value = "/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        ResultData resultData = payFeignApi.updatePay(payDTO);
        return resultData;
    }

    /**
     * openfeign default load balancer
     *
     * @return
     */
    @GetMapping(value = "/feign/pay/get/info")
    public String getInfoByConsul() {
        return payFeignApi.getInfoByConsul();
    }
}
