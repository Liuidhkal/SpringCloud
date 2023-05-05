package com.lfz.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DCloudEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DCloudEurekaServerApplication.class, args);
    }

}
/*
*
java -jar d_cloud_EurekaServer-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
java -jar d_cloud_EurekaServer-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
java -jar d_cloud_EurekaServer-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer3
*
* java -jar .\d_cloud_EurekaServer-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1

* */