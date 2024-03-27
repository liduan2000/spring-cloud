package com.duan.cloud.controller;

import com.duan.cloud.entities.Pay;
import com.duan.cloud.entities.PayDTO;
import com.duan.cloud.resp.ResultData;
import com.duan.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "Pay Microservice Module", description = "Pay CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "add", description = "add")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        log.info(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("successfully inserted record, return value: " + i);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "delete", description = "delete")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        return ResultData.success(i);
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "update", description = "update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);

        int i = payService.update(pay);
        return ResultData.success("Successfully modified record, return value: " + i);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "get by id", description = "get by id")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "get all", description = "get all")
    public ResultData<List<Pay>> getAll() {
        List<Pay> payList = payService.getAll();
        return ResultData.success(payList);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    private String getInfoByConsul(@Value("${duan.info}") String info) {
        return "duanInfo: " + info + "\t" + "port: " + port;
    }
}
