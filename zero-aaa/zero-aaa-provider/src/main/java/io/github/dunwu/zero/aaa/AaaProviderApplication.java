package io.github.dunwu.zero.aaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 */
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("io.github.dunwu.zero.aaa.dao")
@SpringBootApplication(scanBasePackages = "io.github.dunwu.zero.aaa")
public class AaaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AaaProviderApplication.class, args);
    }
}
