package com.duan.cloud.contorller;

import com.duan.cloud.entities.PayDTO;
import com.duan.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {
    private static final String PAY_SRV_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PAY_SRV_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAY_SRV_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping("/consumer/pay/update")
    public ResultData updateOrder(PayDTO payDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PayDTO> entity = new HttpEntity<>(payDTO, headers);
        ResponseEntity<ResultData> response = restTemplate.exchange(PAY_SRV_URL + "/pay/update", HttpMethod.PUT, entity, ResultData.class);
        return response.getBody();
    }

    @GetMapping("/consumer/pay/del/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        ResponseEntity<ResultData> response = restTemplate.exchange(PAY_SRV_URL + "/pay/del/" + id, HttpMethod.DELETE, null, ResultData.class, params);
        return response.getBody();
    }
}
