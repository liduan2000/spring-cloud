package com.duan.cloud.controller;

import com.duan.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

//    @GetMapping(value = "/feign/pay/bulkhead/{id}")
//    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
//    public String myBulkhead(@PathVariable("id") Integer id) {
//        return payFeignApi.myBulkhead(id);
//    }

    @GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadTHREADPOOL(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + "\t" + "enter the method!!!");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "exist the method!!!");

        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + " Bulkhead.Type.THREADPOOL");
    }

    // fallback
    public String myCircuitFallback(Integer id, Throwable t) {
        return "myCircuitFallback: The system is busy, please try again later-----/(ㄒoㄒ)/~~";
    }

//    public String myBulkheadFallback(Integer id, Throwable t) {
//        return "Bulkhead.Type.SEMAPHORE: The system is busy, please try again later-----/(ㄒoㄒ)/~~";
//    }

    public CompletableFuture<String> myBulkheadPoolFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL: The system is busy, please try again later-----/(ㄒoㄒ)/~~");
    }
}
