package com.duan.cloud.apis;

import com.duan.cloud.entities.PayDTO;
import com.duan.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {
    @GetMapping("/pay/add")
    ResultData addPay(PayDTO payDTO);

    @GetMapping("/pay/get/{id}")
    ResultData getPayInfo(@PathVariable("id") Integer id);

    @GetMapping("/pay/update")
    ResultData updatePay(PayDTO payDTO);

    @GetMapping("/pay/del/{id}")
    ResultData deletePay(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/get/info")
    String getInfoByConsul();

    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);
}
