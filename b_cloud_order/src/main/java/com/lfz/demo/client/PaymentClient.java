package com.lfz.demo.client;

import com.lfz.demo.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-payment-service")
public interface PaymentClient {

    @GetMapping("/payment/{id}")
    public Payment payment(@PathVariable("id") Integer id);
}
