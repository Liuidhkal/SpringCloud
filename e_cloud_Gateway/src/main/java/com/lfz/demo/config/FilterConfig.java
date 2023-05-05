package com.lfz.demo.config;

import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Configuration
public class FilterConfig {

    @Bean
    public GlobalFilter a()
    {
        return new AFilter();
    }

    @Bean
    public GlobalFilter b()
    {
        return new BFilter();
    }

    @Bean
    public GlobalFilter c()
    {
        return new CFilter();
    }

    @Bean
    public GlobalFilter myAuthFilter()
    {
        return new MyAuthFilter();
    }


    @Slf4j
    static class AFilter implements GlobalFilter, Ordered{

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            log.info("AFilter前置逻辑");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("AFilter后置逻辑");
            }));

        }

        @Override
        public int getOrder() {
            return HIGHEST_PRECEDENCE + 100;
        }
    }

    @Slf4j
    static class BFilter implements GlobalFilter, Ordered{

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            log.info("BFliter前置逻辑");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("BFilter后置逻辑");
            }));
        }

        @Override
        public int getOrder() {
            return HIGHEST_PRECEDENCE + 200;
        }
    }


    @Slf4j
    static class CFilter implements GlobalFilter, Ordered
    {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
        {
            log.info("CFilter前置逻辑");
            return chain.filter(exchange).then(Mono.fromRunnable(() ->
            {
                log.info("CFilter后置逻辑");
            }));
        }

        @Override
        public int getOrder()
        {
            return HIGHEST_PRECEDENCE + 300;
        }
    }
/*
* 过滤请求中是否有token
* */
    @Slf4j
    static class MyAuthFilter implements GlobalFilter, Ordered{

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            log.info("MyAuthFilter权限过滤器");
            String token = exchange.getRequest().getHeaders().getFirst("token");
            if (StringUtils.isBlank(token)){
                //如果token是空的
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//返回401错误码
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        }

        @Override
        public int getOrder() {
            return HIGHEST_PRECEDENCE + 400;
        }
    }
}
