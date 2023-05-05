package com.lfz.demo.controller;

import com.lfz.demo.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Slf4j
//@CrossOrigin
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/{id}")
    public ResponseEntity<Payment> payment(@PathVariable("id") Integer id)
    {
        Payment payment = new Payment(id, "⽀付成功,服务端口="+serverPort);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/http/{id}")
    public String testHttp(
            @PathVariable("id") Integer id,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestHeader("token") String token
    ){
        log.info("username:{}, password:{}, token: {}", username, password, token);
        return username + "\t" + password + "\t" + token;
    }
}
