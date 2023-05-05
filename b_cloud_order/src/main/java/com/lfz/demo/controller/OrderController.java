package com.lfz.demo.controller;

import com.lfz.demo.client.PaymentClient;
import com.lfz.demo.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Integer id) {

       // List<ServiceInstance> serviceInstances = discoveryClient.getInstances("cloud-payment-service");
       // ServiceInstance serviceInstance = serviceInstances.get(0);

        //String url = "http://localhost:9001/payment/" + id;
        //String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/payment/"+id;

        String url = "http://cloud-payment-service/payment/" + id;
        Payment payment = restTemplate.getForObject(url, Payment.class);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/http/{id}")
    public ResponseEntity<String> http(@PathVariable("id") Integer id){
        //头
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", "token-123456");

        //请求参数
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("username", Collections.singletonList("zhangsan"));
        map.put("password", Collections.singletonList("123"));

        //写入请求参数和头
        HttpEntity httpEntity = new HttpEntity(map, headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9001/payment/http/12345", HttpMethod.POST, httpEntity, String.class);
        return exchange;

    }

    @Resource
    private PaymentClient paymentClient;

    @GetMapping("/feign/payment/{id}")
    public ResponseEntity<Payment> getPaymentByFeign(@PathVariable("id") Integer id){

        Payment payment = paymentClient.payment(id);
        return ResponseEntity.ok(payment);
    }
}
